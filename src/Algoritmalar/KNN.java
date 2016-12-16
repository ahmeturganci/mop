package Algoritmalar;

import javafx.scene.control.Alert;
import weka.classifiers.Evaluation;
import weka.core.Instances;

import java.util.Random;

/**
 * Created by ahmetu on 16.12.2016.
 */
public class KNN {
    public void KNNAlgoritma(Instances train) throws Exception {
        weka.classifiers.lazy.IBk ibk = new weka.classifiers.lazy.IBk();
        ibk.buildClassifier(train);
        Evaluation eval=new Evaluation(train);
        eval.crossValidateModel(ibk, train, 10, new Random(1));

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("BİLGİ");
        alert.setHeaderText(eval.fMeasure(1)+" "+eval.precision(1)+" "+eval.recall(1));
        alert.setContentText(eval.toSummaryString());
        alert.showAndWait();

    }
}
