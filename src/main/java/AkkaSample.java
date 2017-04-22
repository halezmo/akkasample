import actors.*;
import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;
import model.PersistModel;
import model.PersistModelV2;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AkkaSample {

    private static final Logger LOG = LoggerFactory.getLogger(AkkaSample.class);

    public static void main(String[] args) throws InterruptedException {
        ActorSystem actorSystem = ActorSystem.create("newSystem");

        LOG.info("ActorSystem created");

        ActorRef persistenceActor =  actorSystem.actorOf(Props.create(SaveActor.class), "SaveActor");

        persistenceActor.tell(new String("111"), ActorRef.noSender());

        persistenceActor.tell(new String("222"), ActorRef.noSender());

        persistenceActor.tell(new String("333"), ActorRef.noSender());

        persistenceActor.tell(new PersistModel("pm"), ActorRef.noSender());

        persistenceActor.tell(new PersistModelV2("pm2"), ActorRef.noSender());

        Thread.sleep(100000);
        actorSystem.terminate();
    }
}