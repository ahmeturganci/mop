package Controller;

import Algoritmalar.KNN;
import Algoritmalar.NB;
import Algoritmalar.NBNET;
import Algoritmalar.SMO;
import Islemler.Islem;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.scene.control.*;
import javafx.stage.FileChooser;
import Main.Main;
import weka.core.Instances;
import weka.core.converters.ConverterUtils;

public class MainController implements Initializable {

    Instances train = null;
    File dosya = null;
    Islem islem;
    // ToggleGroup group;
    @FXML
    private RadioButton rbBayes;
    @FXML
    private RadioButton rbKnn;
    @FXML
    private RadioButton rbSmo;
    @FXML
    private RadioButton rbBayesNet;

    @FXML
    private TextArea txtTest;
    @FXML
    private TextArea txtaSummary;
    @FXML
    private Label lblMeasure;
    @FXML
    private Label lblPrecision;
    @FXML
    private Label lblRecall;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        islem=Islem.getIslem();
    }

    public void dosyaAction() throws IOException, Exception {
        FileChooser dosyaSec = new FileChooser();
        dosyaSec.setTitle("arff Uzantılı Dosya Aç");
        dosyaSec.getExtensionFilters().add(new FileChooser.ExtensionFilter("arff", "*.arff"));
        dosya = dosyaSec.showOpenDialog(Main.stage);
        System.out.println(dosya.getAbsolutePath());
        ConverterUtils.DataSource source;
        source = new ConverterUtils.DataSource(dosya.getAbsolutePath());
        train = source.getDataSet();
        if (train.classIndex() == -1)
            train.setClassIndex(train.numAttributes() - 1);
    }

    public void baslatAction() throws Exception {
        if (rbBayes.isSelected()) {
            NB nb = new NB();
            nb.naiveBayes(train);
            //txtTest.setText(islem.getToSummaryString());
            txtaSummary.setText(islem.getToSummaryString());
            lblMeasure.setText(islem.getfMeasure());
            lblPrecision.setText(islem.getPrecision());
            lblRecall.setText(islem.getRecall());
           /* Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("BİLGİ");
            alert.setHeaderText(islem.getToSummaryString());
            alert.showAndWait();*/

        } else if (rbKnn.isSelected()) {
            KNN knn = new KNN();
            knn.KNNAlgoritma(train);
        } else if (rbSmo.isSelected()) {
            SMO smo = new SMO();
            smo.SMOAlgoritma(train);
        } else if (rbBayesNet.isSelected()) {
            NBNET nbn = new NBNET();
            nbn.NBNETAlgoritma(train);
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Uyarı");
            alert.setContentText("Algoritma şeçimi yapınız.");
            alert.showAndWait();
        }
    }
}



