package Algoritmalar;

import Islemler.Islem;
import javafx.scene.control.Alert;
import weka.classifiers.Evaluation;
import weka.core.Instances;

import java.util.Random;

/**
 * Created by ahmetu on 15.12.2016.
 */
public class NBNET {
    public void NBNETAlgoritma (Instances train) throws Exception {
        Islem i = Islem.getIslem();
        weka.classifiers.bayes.BayesNet bnet = new weka.classifiers.bayes.BayesNet();
        bnet.buildClassifier(train);
        Evaluation eval=new Evaluation(train);
        eval.crossValidateModel(bnet, train, 10, new Random(1));
        i.setToSummaryString(eval.toSummaryString()+"\n"+eval.toClassDetailsString()+"\n"+eval.toMatrixString());
        i.setfMeasure(eval.fMeasure(1)+"");
        i.setPrecision(eval.precision(1)+"");
        i.setRecall(eval.recall(1)+"");
        i.setCrrRate(eval.pctCorrect());

        /*Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("BİLGİ");
        alert.setHeaderText(eval.fMeasure(1)+" "+eval.precision(1)+" "+eval.recall(1));
        alert.setContentText(eval.toSummaryString());
        alert.showAndWait();*/

    }
}
