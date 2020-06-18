package gui;

import java.io.IOException;
import java.net.URL;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

import application.Main;
import db.DbIntegrityException;
import gui.listeners.DataChangeListener;
import gui.util.Alerts;
import gui.util.Utils;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Callback;
import model.entities.Parametro;
import model.entities.PrimaryKeyChange;
import model.services.ParametroService;
import model.services.PrimaryKeyChangeService;

public class PrimaryKeyChangeListController implements Initializable, DataChangeListener {
	
	private PrimaryKeyChangeService service;
	
	private ParametroService parametroService;
	
	private Parametro parametro;

	@FXML
	public TableView<PrimaryKeyChange> tableViewPrimaryKeyChange;
	
	@FXML
	public TableColumn<PrimaryKeyChange, String> tableColumnCod_empresa;
	
	@FXML
	public TableColumn<PrimaryKeyChange, String> tableColumnCod_estab;

	@FXML
	public TableColumn<PrimaryKeyChange, Date> tableColumnData_fiscal;
	
	@FXML
	public TableColumn<PrimaryKeyChange, Date> tableColumnData_emissao;

	@FXML
	public TableColumn<PrimaryKeyChange, String> tableColumnMovto_e_s;
	
	@FXML
	public TableColumn<PrimaryKeyChange, String> tableColumnNorm_dev;

	@FXML
	public TableColumn<PrimaryKeyChange, String> tableColumnCod_fis_jur;
	
	@FXML
	public TableColumn<PrimaryKeyChange, String> tableColumnNum_docfis;
	
	@FXML
	public TableColumn<PrimaryKeyChange, String> tableColumnSerie_docfis;
	
	@FXML
	public TableColumn<PrimaryKeyChange, String> tableColumnSub_serie_docfis;
	
	@FXML
	public TableColumn<PrimaryKeyChange, PrimaryKeyChange> tableColumnEDIT;

	@FXML
	public TableColumn<PrimaryKeyChange, PrimaryKeyChange> tableColumnREMOVE;
	
	@FXML
	public Button btAtualizar;
	
	@FXML
	public Button btExecutar;
	
	@FXML
	private ComboBox<Parametro> comboBoxParametro;
	
	@FXML
	private DatePicker dataInicial;
	
	@FXML
	private DatePicker dataFinal;
	
	@FXML
	public void onBtAtualizarAction(ActionEvent event) {
//		Stage parentStage = Utils.currentStage(event);
//		PrimaryKeyChange obj = new PrimaryKeyChange();
//		createDialogForm(obj, "/gui/PrimaryKeyChangeForm.fxml", parentStage);

		atualizaRegistros();
	}
	 
	@FXML
	public void onBtExecutarAction(ActionEvent event) {
		executaCorrecao();
		Alerts.showAlert("IO Exception", "Information loading view", "Execution Complete!", AlertType.INFORMATION);
	}

	private ObservableList<PrimaryKeyChange> obsList;
	
	private ObservableList<Parametro> obsListParametro;
	
	public void setParametro(Parametro parametro) {
		this.parametro = parametro;
	}

	public Parametro getParametro() {
		return parametro;
	}

	public void setPrimaryKeyChangeService(PrimaryKeyChangeService service, ParametroService parametroService) {
		this.service = service;
		this.parametroService = parametroService;
	}
	
	@Override
	public void initialize(URL url, ResourceBundle rb) {
		initializeNodes();
	}

	private void initializeNodes() {
	
		tableColumnCod_empresa.setCellValueFactory(new PropertyValueFactory<>("cod_empresa"));
		tableColumnCod_estab.setCellValueFactory(new PropertyValueFactory<>("cod_estab"));
		tableColumnData_fiscal.setCellValueFactory(new PropertyValueFactory<>("data_fiscal"));
		Utils.formatTableColumnDate(tableColumnData_fiscal, "dd/MM/yyyy");
		tableColumnData_emissao.setCellValueFactory(new PropertyValueFactory<>("data_emissao"));
		Utils.formatTableColumnDate(tableColumnData_emissao, "dd/MM/yyyy");
		tableColumnMovto_e_s.setCellValueFactory(new PropertyValueFactory<>("movto_e_s"));
		tableColumnNorm_dev.setCellValueFactory(new PropertyValueFactory<>("norm_dev"));
		tableColumnCod_fis_jur.setCellValueFactory(new PropertyValueFactory<>("cod_fis_jur"));
		tableColumnNum_docfis.setCellValueFactory(new PropertyValueFactory<>("num_docfis"));
		tableColumnSerie_docfis.setCellValueFactory(new PropertyValueFactory<>("serie_docfis"));
		tableColumnSub_serie_docfis.setCellValueFactory(new PropertyValueFactory<>("sub_serie_docfis"));	
		
		Utils.formatDatePicker(dataInicial, "dd/MM/yyyy");
		Utils.formatDatePicker(dataFinal, "dd/MM/yyyy");
		
		//Macete para que o tableview acompanhe a altura da janela.
		Stage stage = (Stage) Main.getMainScene().getWindow();
		tableViewPrimaryKeyChange.prefHeightProperty().bind(stage.heightProperty());

		initializecomboBoxParametro();
	}
	
	public void updateTableView() {
		if (service == null) {
			throw new IllegalStateException("Service was null!");
		}
		List<PrimaryKeyChange> list = service.findAll(getParametro());
		obsList = FXCollections.observableArrayList(list);
        //Não esquecer de relacionar o tableViewPrimaryKeyChange no sceneBuilder campo id
		tableViewPrimaryKeyChange.setItems(obsList);
		initEditButtons();
		initRemoveButtons();
	}

	public void atualizaRegistros() {
		parameterData();
		parametroService.Atualiza(getParametro());
		updateTableView();
	}
	
	public void executaCorrecao() {
		parameterData();
		parametroService.Execute(getParametro());
		updateTableView();
	}
	
	private void createDialogForm(PrimaryKeyChange obj, String absoluteName, Stage parenStage) {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource(absoluteName));
			Pane pane = loader.load();
			
			PrimaryKeyChangeFormController controller = loader.getController();
			controller.setPrimaryKeyChange(obj);
			controller.setPrimaryKeyChangeService(new PrimaryKeyChangeService());
			controller.subscribeDataChangeListener(this);
			controller.updateFormData();
			
			Stage dialogStage = new Stage();
			dialogStage.setTitle("Enter primary key data");
			dialogStage.setScene(new Scene(pane));
			dialogStage.setResizable(false);
			dialogStage.initOwner(parenStage);//Janela Pai
			dialogStage.initModality(Modality.WINDOW_MODAL);
			dialogStage.showAndWait();

		} 
		catch (IOException e) {
			e.printStackTrace();
			Alerts.showAlert("IO Exception", "Error loading view", e.getMessage(), AlertType.ERROR);
		}
	}

	@Override
	public void onDataChange() {
		updateTableView();
	}
	
	private void initEditButtons() {
		tableColumnEDIT.setCellValueFactory(param -> new ReadOnlyObjectWrapper<>(param.getValue()));
		tableColumnEDIT.setCellFactory(param -> new TableCell<PrimaryKeyChange, PrimaryKeyChange>() {
			private final Button button = new Button("edit");

			@Override
			protected void updateItem(PrimaryKeyChange obj, boolean empty) {
				super.updateItem(obj, empty);
				if (obj == null) {
					setGraphic(null);
					return;
				}
				setGraphic(button);
				button.setOnAction(
						event -> createDialogForm(obj, "/gui/PrimaryKeyChangeForm.fxml", Utils.currentStage(event)));
			}
		});
	}

	private void initRemoveButtons() {
		tableColumnREMOVE.setCellValueFactory(param -> new ReadOnlyObjectWrapper<>(param.getValue()));
		tableColumnREMOVE.setCellFactory(param -> new TableCell<PrimaryKeyChange, PrimaryKeyChange>() {
			private final Button button = new Button("remove");

			@Override
			protected void updateItem(PrimaryKeyChange obj, boolean empty) {
				super.updateItem(obj, empty);
				if (obj == null) {
					setGraphic(null);
					return;
				}
				setGraphic(button);
				button.setOnAction(event -> removeEntity(obj));
			}
		});
	}

	private void removeEntity(PrimaryKeyChange obj) {
		Optional<ButtonType> result = Alerts.showConfirmation("Confirmation", "Are you sure to delete?");
		
		if (result.get() == ButtonType.OK) {
			if (service == null) {
				throw new IllegalStateException("Sevice was null");
			}
			try {
				service.remove(obj);
				updateTableView();
			}
			catch (DbIntegrityException e) {
				Alerts.showAlert("Error removing object", null, e.getMessage(), AlertType.ERROR);
			}
		}
	}
	
	private void initializecomboBoxParametro() {
		Callback<ListView<Parametro>, ListCell<Parametro>> factory = lv -> new ListCell<Parametro>() {
			@Override
			protected void updateItem(Parametro item, boolean empty) {
				super.updateItem(item, empty);
				setText(empty ? "" : item.getCodEstab() + " - " + item.getRazaoSocial());
			}
		};
		comboBoxParametro.setCellFactory(factory);
		comboBoxParametro.setButtonCell(factory.call(null));
		
		//obj.setDepartment(comboBoxDepartment.getValue());
		
	}

	public void parameterFirstData() {
		Parametro pr = new Parametro();
		
		comboBoxParametro.getSelectionModel().selectFirst();
		
		pr = comboBoxParametro.getValue();
		
		if (pr.getDataInicial() != null) {
			dataInicial.setValue((LocalDateTime.ofInstant(pr.getDataInicial().toInstant(), ZoneId.systemDefault()).toLocalDate()));
		}
		
		if (pr.getDataFinal() != null) {
			dataFinal.setValue((LocalDateTime.ofInstant(pr.getDataFinal().toInstant(), ZoneId.systemDefault()).toLocalDate()));
		}
		
		setParametro(pr);
		
//		if (dataInicial.getValue() == null ) {
//			exception.addErrors("dataInicial", "Field can't be empty");
//		}
//		else {
//			Instant instant = Instant.from(dataInicial.getValue().atStartOfDay(ZoneId.systemDefault()));
//			pr.setDataInicial(Date.from(instant));	
//		}
		
	}
	
	public void parameterData() {
		Parametro pr = new Parametro();
		
		pr = comboBoxParametro.getValue();
		
		Instant instant1 = Instant.from(dataInicial.getValue().atStartOfDay(ZoneId.systemDefault()));
		pr.setDataInicial(Date.from(instant1));

		Instant instant2 = Instant.from(dataFinal.getValue().atStartOfDay(ZoneId.systemDefault()));
		pr.setDataFinal(Date.from(instant2));
		
		setParametro(pr);
		
	}

	public void updateFormData() {
		if (parametro == null) {
			throw new IllegalStateException("Parametro was null");
		}
		
		if (parametro.getDataInicial() != null) {
			dataInicial.setValue((LocalDateTime.ofInstant(parametro.getDataInicial().toInstant(), ZoneId.systemDefault()).toLocalDate()));
		}
		if (parametro.getDataFinal() != null) {
			dataFinal.setValue((LocalDateTime.ofInstant(parametro.getDataFinal().toInstant(), ZoneId.systemDefault()).toLocalDate()));
		}
		
		comboBoxParametro.getSelectionModel().selectFirst();
		
//		if (parametro == null) {
//			comboBoxDepartment.getSelectionModel().selectFirst();
//		}
//		else {
//			comboBoxDepartment.setValue(entity.getDepartment());
//		}
//		
	}
	
//	public Parametro getFirstComBoxParametro() {
//		comboBoxParametro.getSelectionModel().selectFirst();
//		return comboBoxParametro.getValue();
//	}
	
	public void loadAssociatedObject() {
		if (parametroService == null) {
			throw new IllegalStateException("LojaService was null");
		}
		List<Parametro> list = parametroService.findAll();
		obsListParametro = FXCollections.observableArrayList(list);
		comboBoxParametro.setItems(obsListParametro);
		
	}
	
}
