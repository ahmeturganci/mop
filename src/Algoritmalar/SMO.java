package Algoritmalar;


import javafx.scene.control.Alert;
import weka.classifiers.Evaluation;
import weka.core.Instances;

import java.util.Random;

public class SMO {

    public  void  SMOAlgoritma(Instances train) throws Exception {

            weka.classifiers.functions.SMO smo = new weka.classifiers.functions.SMO();
            smo.buildClassifier(train);
            Evaluation eval=new Evaluation(train);
            eval.crossValidateModel(smo, train, 10, new Random(1));

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("BİLGİ");
            alert.setHeaderText(eval.fMeasure(1)+" "+eval.precision(1)+" "+eval.recall(1));
            alert.setContentText(eval.toSummaryString());
            alert.showAndWait();
        }
    }

