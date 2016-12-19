package Controller;

import Algoritmalar.KNN;
import Algoritmalar.NB;
import Algoritmalar.NBNET;
import Algoritmalar.SMO;
import Islemler.Islem;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.scene.chart.*;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
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
    private BarChart<?, ?> grafikAlgoritma;
    @FXML
    private LineChart<?, ?> grafikAttr;

    @FXML
    private TextArea txtK;

    @FXML
    private ListView<?> listAttr;
    @FXML
    private TextArea txtAttr;

    XYChart.Series bayesSeries;
    XYChart.Series naiveSeries;
    XYChart.Series knnSeries;
    XYChart.Series smoSeries;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        islem = Islem.getIslem();
        txtK.setText("1");
        txtK.setEditable(false);
    }

    public void dosyaAction() throws IOException {
        bayesSeries = new XYChart.Series();
        naiveSeries = new XYChart.Series();
        knnSeries = new XYChart.Series();
        smoSeries = new XYChart.Series();

        FileChooser dosyaSec = new FileChooser();
        dosyaSec.setTitle("arff Uzantılı Dosya Aç");
        dosyaSec.getExtensionFilters().add(new FileChooser.ExtensionFilter("arff", "*.arff"));
        dosya = dosyaSec.showOpenDialog(Main.stage);
        System.out.println(dosya.getAbsolutePath());
        try {
            olustur();
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(train.classAttribute().name());
        //System.out.println(train.numAttributes());
        //System.out.println(train.numClasses());
        //System.out.println(train.numInstances());
    }

    public void olustur() throws Exception {
        ConverterUtils.DataSource source;
        source = new ConverterUtils.DataSource(dosya.getAbsolutePath());
        train = source.getDataSet();
        if (train.classIndex() == -1)
            train.setClassIndex(train.numAttributes() - 1);
    }

    public void baslatAction() throws Exception {
        attrListDoldur();
        if (rbBayes.isSelected()) {
            NB nb = new NB();
            nb.naiveBayes(train);
            //txtTest.setText(islem.getToSummaryString());
            yazdir("Naïve Bayes Sınıflandırıcı adını İngiliz matematikçi Thomas Bayes'ten (yak. 1701 - 7 Nisan 1761) alır.  Naïve Bayes Sınıflandırıcı Örüntü tanıma problemine ilk bakışta oldukça kısıtlayıcı görülen bir önerme ile kullanılabilen olasılıkcı bir yaklaşımdır. Bu önerme örüntü tanıma da kullanılacak her bir tanımlayıcı öznitelik ya da parametrenin istatistik açıdan bağımsız olması gerekliliğidir. Her ne kadar  bu önerme Naive Bayes sınıflandırıcının kullanım alanını kısıtlasa da, genelde istatistik bağımsızlık  koşulu esnetilerek kullanıldığında da daha karmaşık Yapay sinir ağları gibi metotlarla karşılaştırabilir sonuçlar vermektedir. Bir Naive Bayes sınıflandırıcı, her özniteliğin birbirinden koşulsal bağımsız olduğu ve öğrenilmek istenen kavramın tüm bu özniteliklere koşulsal bağlı olduğu bir Bayes ağı olarak da düşünülebilir.\n");
            bayesSeries.getData().add(new XYChart.Data("Naive\n"+islem.getCrrRate(), islem.getCrrRate()));
            System.out.println(islem.getCrrRate());
            grafikAlgoritma.getData().addAll(bayesSeries);
        } else if (rbKnn.isSelected()) {
            int k=Integer.parseInt(txtK.getText());
            System.out.println(k);
            if(k>=1 && k%2 !=0) {
                KNN knn = new KNN();
                knn.KNNAlgoritma(train, k);
                yazdir("KNN");
                knnSeries.getData().add(new XYChart.Data("Knn\n"+islem.getCrrRate(), islem.getCrrRate()));
                grafikAlgoritma.getData().addAll(knnSeries);
                System.out.println(islem.getCrrRate());
            }
        } else if (rbSmo.isSelected()) {
            SMO smo = new SMO();
            smo.SMOAlgoritma(train);
            yazdir("SMO");
            smoSeries.getData().add(new XYChart.Data("Smo\n"+islem.getCrrRate(), islem.getCrrRate()));
            grafikAlgoritma.getData().addAll(smoSeries);
            System.out.println(islem.getCrrRate());
        } else if (rbBayesNet.isSelected()) {
            NBNET nbn = new NBNET();
            nbn.NBNETAlgoritma(train);
            yazdir("NBNET");
            naiveSeries.getData().add(new XYChart.Data("BayesNET\n"+islem.getCrrRate(), islem.getCrrRate()));
            grafikAlgoritma.getData().addAll(naiveSeries);
            System.out.println(islem.getCrrRate());
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Uyarı");
            alert.setContentText("Algoritma şeçimi yapınız.");
            alert.showAndWait();
        }
    }

    public void yazdir(String aciklama) {
        txtaSummary.setText(islem.getToSummaryString() + "\n" + "Fmeasure : " + islem.getfMeasure()
                + "\n" + "Precision : " + islem.getPrecision() + "\n" + "Recall : " + islem.getRecall()
                + "\n\nClass Attiribute : " + train.classAttribute().toString().substring(18, train.classAttribute().toString().length() - 1));
        txtAciklama.setText(aciklama);

    }

    public void attrListDoldur() {
        listAttr.getItems().clear();
        ObservableList items = listAttr.getItems();
        for (int i = 0; i < train.numAttributes(); i++) {
            items.add(train.attribute(i).name());
        }
    }

    public void handleMouseClick(MouseEvent arg0) {
        attrTxtDoldur(listAttr.getSelectionModel().getSelectedIndex());
    }

    public void attrTxtDoldur(int i) {
        //txtAttr.setText("");
        txtAttr.setText("Name : " + train.attribute(i).name()
                + "\nType : " + train.attribute(i).type()
                + "\nDistinct : " + train.attributeStats(i).distinctCount
                + "\nMissing : " + train.attributeStats(i).missingCount
                + "\nUnique : " + train.attributeStats(i).uniqueCount);
        XYChart.Series series = new XYChart.Series();
        series.getData().add(new XYChart.Data("Distinct", train.attributeStats(i).distinctCount));
        series.getData().add(new XYChart.Data("Missing", train.attributeStats(i).missingCount));
        series.getData().add(new XYChart.Data("Unique", train.attributeStats(i).uniqueCount));
        grafikAttr.getData().addAll(series);
    }

    @FXML
    public void radioBayesNET(ActionEvent event) {
        if (rbBayesNet.isSelected()) {
            rbKnn.setSelected(false);
            rbSmo.setSelected(false);
            rbBayes.setSelected(false);
            txtK.setEditable(false);
        }
    }

    @FXML
    public void radioKNN(ActionEvent event) {
        if (rbKnn.isSelected()) {
            rbSmo.setSelected(false);
            rbBayes.setSelected(false);
            rbBayesNet.setSelected(false);
            txtK.setEditable(true);
        }
    }

    @FXML
    public void radioNaive(ActionEvent event) {
        if (rbBayes.isSelected()) {
            rbKnn.setSelected(false);
            rbSmo.setSelected(false);
            rbBayesNet.setSelected(false);
            txtK.setEditable(false);
        }
    }

    @FXML
    public void radioSMO(ActionEvent event) {
        if (rbSmo.isSelected()) {
            rbBayes.setSelected(false);
            rbBayesNet.setSelected(false);
            rbKnn.setSelected(false);
            txtK.setEditable(false);
        }
    }

}



