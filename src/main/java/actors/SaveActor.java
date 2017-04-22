package actors;

import akka.event.Logging;
import akka.event.LoggingAdapter;
import akka.persistence.AbstractPersistentActor;
import model.PersistModel;
import model.PersistModelV2;

import java.util.ArrayList;


/**
 * Created by Hale on 22.04.2017.
 */
public class SaveActor extends AbstractPersistentActor {

    private final LoggingAdapter log = Logging.getLogger(context().system(), this);

    private final ArrayList<String> items = new ArrayList<>();
    private final ArrayList<PersistModelV2> persistModels = new ArrayList<>();

    @Override
    public Receive createReceiveRecover() {
        return receiveBuilder()
                .match(String.class, s -> {
                    items.add(s);
                    log.info("recovered: " + s + " count: " + items.size() );
                }).match(PersistModel.class, s -> {
                    persistModels.add(new PersistModelV2(s));
                    log.info("recoveredPM: " + s.getName() + " count: " + persistModels.size() );
                }).match(PersistModelV2.class, s -> {
                    persistModels.add(s);
                    log.info("recoveredPM2: " + s.getName() + " count: " + persistModels.size() );
                })
                .build();
    }

    @Override
    public Receive createReceive() {
        return receiveBuilder()
                .match(String.class, msg -> {
                    log.info(msg);
                    persist("handle-" + msg, e -> items.add(e));
                })
                .match(PersistModelV2.class, msg -> {
                    log.info("PM2 " + msg.getName());
                    persist(msg, e -> persistModels.add(msg));
                })
                .match(PersistModel.class, msg -> {
                    log.info("PM " + msg.getName());
                    persist(msg, e -> persistModels.add(new PersistModelV2(msg)));
                })
                .build();
    }

    @Override
    public String persistenceId() {
        return "SaveActorPersistence";
    }
}