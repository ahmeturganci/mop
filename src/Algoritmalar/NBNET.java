package Algoritmalar;

import javafx.scene.control.Alert;
import weka.classifiers.Evaluation;
import weka.core.Instances;

import java.util.Random;

/**
 * Created by ahmetu on 15.12.2016.
 */
public class NBNET {
    public void NBNETAlgoritma (Instances train) throws Exception {
        weka.classifiers.bayes.BayesNet bnet = new weka.classifiers.bayes.BayesNet();
        bnet.buildClassifier(train);
        Evaluation eval=new Evaluation(train);
        eval.crossValidateModel(bnet, train, 10, new Random(1));

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("HATA");
        alert.setHeaderText(eval.fMeasure(1)+" "+eval.precision(1)+" "+eval.recall(1));
        alert.setContentText(eval.toSummaryString());
        alert.showAndWait();

    }
}
