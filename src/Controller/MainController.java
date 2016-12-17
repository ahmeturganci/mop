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

import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
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
    private TextArea txtAciklama;
    @FXML
    private TextArea txtaSummary;
    @FXML
    private Label lblMeasure;
    @FXML
    private Label lblPrecision;
    @FXML
    private Label lblRecall;
    @FXML
    private LineChart<?, ?> grafik;
    @FXML
    private CategoryAxis x;

    @FXML
    private NumberAxis y;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        islem=Islem.getIslem();
        XYChart.Series series=new XYChart.Series();
        series.getData().add(new XYChart.Data("1",23));

        grafik.getData().addAll(series);
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
            yazdir("Naïve Bayes Sınıflandırıcı adını İngiliz matematikçi Thomas Bayes'ten (yak. 1701 - 7 Nisan 1761) alır.  Naïve Bayes Sınıflandırıcı Örüntü tanıma problemine ilk bakışta oldukça kısıtlayıcı görülen bir önerme ile kullanılabilen olasılıkcı bir yaklaşımdır. Bu önerme örüntü tanıma da kullanılacak her bir tanımlayıcı öznitelik ya da parametrenin istatistik açıdan bağımsız olması gerekliliğidir. Her ne kadar  bu önerme Naive Bayes sınıflandırıcının kullanım alanını kısıtlasa da, genelde istatistik bağımsızlık  koşulu esnetilerek kullanıldığında da daha karmaşık Yapay sinir ağları gibi metotlarla karşılaştırabilir sonuçlar vermektedir. Bir Naive Bayes sınıflandırıcı, her özniteliğin birbirinden koşulsal bağımsız olduğu ve öğrenilmek istenen kavramın tüm bu özniteliklere koşulsal bağlı olduğu bir Bayes ağı olarak da düşünülebilir.\n");
        } else if (rbKnn.isSelected()) {
            KNN knn = new KNN();
            knn.KNNAlgoritma(train);
            yazdir("KNN");
        } else if (rbSmo.isSelected()) {
            SMO smo = new SMO();
            smo.SMOAlgoritma(train);
            yazdir("SMO");
        } else if (rbBayesNet.isSelected()) {
            NBNET nbn = new NBNET();
            nbn.NBNETAlgoritma(train);
            yazdir("NBNET");
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Uyarı");
            alert.setContentText("Algoritma şeçimi yapınız.");
            alert.showAndWait();
        }
    }
    public void yazdir(String aciklama){
        txtaSummary.setText(islem.getToSummaryString());
        lblMeasure.setText(islem.getfMeasure());
        lblPrecision.setText(islem.getPrecision());
        lblRecall.setText(islem.getRecall());
        txtAciklama.setText(aciklama);
    }
}



