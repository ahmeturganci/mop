package Algoritmalar;

import java.util.Random;
import javafx.scene.control.Alert;
import weka.classifiers.Evaluation;
import weka.core.Instances;

public class NB
{
    public void naiveBayes(Instances train) throws Exception{
        weka.classifiers.bayes.NaiveBayes nb=new weka.classifiers.bayes.NaiveBayes();
        nb.buildClassifier(train);
        Evaluation eval=new Evaluation(train);
        eval.crossValidateModel(nb, train, 10, new Random(1));


        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("HATA");
        alert.setHeaderText(eval.fMeasure(1)+" "+eval.precision(1)+" "+eval.recall(1));
        alert.setContentText(eval.toSummaryString());
        alert.showAndWait();
    }
}
