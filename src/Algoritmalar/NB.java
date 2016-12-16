package Algoritmalar;

import java.util.Random;

import Islemler.Islem;
import javafx.scene.control.Alert;
import weka.classifiers.Evaluation;
import weka.core.Instances;

public class NB
{
    public void naiveBayes(Instances train) throws Exception{
        Islem i = Islem.getIslem();

        weka.classifiers.bayes.NaiveBayes nb=new weka.classifiers.bayes.NaiveBayes();
        nb.buildClassifier(train);
        Evaluation eval=new Evaluation(train);
        eval.crossValidateModel(nb, train, 10, new Random(1));

        i.setToSummaryString(eval.toSummaryString());
        i.setfMeasure(eval.fMeasure(1)+"");
        i.setPrecision(eval.precision(1)+"");
        i.setRecall(eval.recall(1)+"");
        /*Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("BİLGİ");
       // alert.setHeaderText(eval.fMeasure(1)+" "+" "+eval.precision(1)+" : precision"+eval.recall(1));
        alert.setContentText(i.getToSummaryString());
        alert.showAndWait();*/
    }
}
