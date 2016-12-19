package Algoritmalar;

import Islemler.Islem;
import javafx.scene.control.Alert;
import weka.classifiers.Evaluation;
import weka.core.Instances;

import java.util.Random;

/**
 * Created by ahmetu on 16.12.2016.
 */
public class KNN {
    public void KNNAlgoritma(Instances train,int k) throws Exception {

        Islem i = Islem.getIslem();

        weka.classifiers.lazy.IBk ibk = new weka.classifiers.lazy.IBk(k);
        ibk.buildClassifier(train);
        Evaluation eval=new Evaluation(train);
        eval.crossValidateModel(ibk, train, 10, new Random(1));
        i.setToSummaryString(eval.toSummaryString()+"\n"+eval.toClassDetailsString()+"\n"+eval.toMatrixString());
        i.setfMeasure(eval.fMeasure(1)+"");
        i.setPrecision(eval.precision(1)+"");
        i.setRecall(eval.recall(1)+"");
        i.setCrrRate(100-eval.errorRate()*100);

        /*Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("BİLGİ");
        alert.setHeaderText(eval.fMeasure(1)+" "+eval.precision(1)+" "+eval.recall(1));
        alert.setContentText(eval.toSummaryString());
        alert.showAndWait();*/

    }
}
