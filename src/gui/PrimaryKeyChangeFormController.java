package gui;

import java.net.URL;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.Set;

import db.DbException;
import gui.listeners.DataChangeListener;
import gui.util.Alerts;
import gui.util.Constraints;
import gui.util.Utils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import model.entities.PrimaryKeyChange;
import model.exceptions.ValidationException;
import model.services.PrimaryKeyChangeService;

public class PrimaryKeyChangeFormController implements Initializable {

	private PrimaryKeyChange entity;

	private PrimaryKeyChangeService service;

	private List<DataChangeListener> dataChangeListeners = new ArrayList<>();

	@FXML
	private TextField txtCod_empresa;

	@FXML
	private TextField txtCod_estab;

	@FXML
	private DatePicker dpData_fiscal;

	@FXML
	private DatePicker dpData_emissao;
	
	@FXML
	private TextField txtMovto_e_s;

	@FXML
	private TextField txtNorm_dev;
	
	@FXML
	private TextField txtCod_fis_jur;

	@FXML
	private TextField txtNum_docfis;
	
	@FXML
	private TextField txtSerie_docfis;

	@FXML
	private TextField txtSub_Serie_docfis;
	
	@FXML
	private TextField txtCod_empresa_aj;

	@FXML
	private TextField txtCod_estab_aj;

	@FXML
	private DatePicker dpData_fiscal_aj;

	@FXML
	private DatePicker dpData_emissao_aj;
	
	@FXML
	private TextField txtMovto_e_s_aj;

	@FXML
	private TextField txtNorm_dev_aj;
	
	@FXML
	private TextField txtCod_fis_jur_aj;

	@FXML
	private TextField txtNum_docfis_aj;
	
	@FXML
	private TextField txtSerie_docfis_aj;

	@FXML
	private TextField txtSub_Serie_docfis_aj;

	@FXML
	private TextField txtCod_class_docfis_aj;

	@FXML
	private TextField txtCod_docto_aj;
	
	@FXML
	private TextField txtCod_modelo_aj;

	@FXML
	private TextField txtCod_modelo_cotepe_aj;
	
	@FXML
	private TextField txtNum_autentic_nfe_aj;

	@FXML
	private Button btSave;

	@FXML
	private Button btCancel;

	public void setPrimaryKeyChange(PrimaryKeyChange entity) {
		this.entity = entity;
	}

	public void setPrimaryKeyChangeService(PrimaryKeyChangeService service) {
		this.service = service;
	}

	public void subscribeDataChangeListener(DataChangeListener listener) {
		dataChangeListeners.add(listener);
	}

	@FXML
	public void onBtSaveAction(ActionEvent event) {
		if (entity == null) {
			throw new IllegalStateException("Entity was null");
		}
		if (service == null) {
			throw new IllegalStateException("Service was null");
		}
		try {
			entity = getFormData();
			service.Update(entity);
			notifyDataChangeListeners();
			Utils.currentStage(event).close();
		} 
		catch (ValidationException e) {
			setErrorMessages(e.getErrors());
		}
		catch (DbException e) {
			Alerts.showAlert("Error saving object", null, e.getMessage(), AlertType.ERROR);
		}

	}

	private void notifyDataChangeListeners() {
		for (DataChangeListener listener : dataChangeListeners) {
			listener.onDataChange();
		}
	}

	private PrimaryKeyChange getFormData() {
		PrimaryKeyChange obj = new PrimaryKeyChange();

		ValidationException exception = new ValidationException("Validation error");	

//		obj.setId(Utils.tryParseToInt(txtId.getText()));
//
//		if (txtName.getText() == null || txtName.getText().trim().equals("")) {
//			exception.addErrors("name", "Field can't be empty");
//		}
//		obj.setName(txtName.getText());

		obj.setCod_empresa(txtCod_empresa.getText());
		obj.setCod_estab(txtCod_estab.getText());
		
		if (dpData_fiscal.getValue() == null ) {
			exception.addErrors("Datafiscal", "Field can't be empty");
		}
		else {
			Instant instant = Instant.from(dpData_fiscal.getValue().atStartOfDay(ZoneId.systemDefault()));
			obj.setData_fiscal(Date.from(instant));
		}
		
		if (dpData_emissao.getValue() == null ) {
			exception.addErrors("DataEmissão", "Field can't be empty");
		}
		else {
			Instant instant = Instant.from(dpData_emissao.getValue().atStartOfDay(ZoneId.systemDefault()));
			obj.setData_emissao(Date.from(instant));	
		}		

		obj.setMovto_e_s(txtMovto_e_s.getText());
		obj.setNorm_dev(txtNorm_dev.getText());
		obj.setCod_fis_jur(txtCod_fis_jur.getText());
		obj.setNum_docfis(txtNum_docfis.getText());
		obj.setSerie_docfis(txtSerie_docfis.getText());
		obj.setSub_serie_docfis(txtSub_Serie_docfis.getText());
		//
		obj.setCod_empresa_aj(txtCod_empresa_aj.getText());
		obj.setCod_estab_aj(txtCod_estab_aj.getText());

		if (dpData_fiscal_aj.getValue() == null ) {
			//exception.addErrors("Datafiscal_Aj", "Field can't be empty");
		obj.setData_fiscal_aj(null);	
		}
		else {
			Instant instant = Instant.from(dpData_fiscal_aj.getValue().atStartOfDay(ZoneId.systemDefault()));
			obj.setData_fiscal_aj(Date.from(instant));	
		}
		
		if (dpData_emissao_aj.getValue() == null ) {
			//exception.addErrors("DataEmissão_Aj", "Field can't be empty");
			obj.setData_emissao_aj(null);
		}
		else {
			Instant instant = Instant.from(dpData_emissao_aj.getValue().atStartOfDay(ZoneId.systemDefault()));
			obj.setData_emissao_aj(Date.from(instant));
		}

		obj.setMovto_e_s_aj(txtMovto_e_s_aj.getText());
		obj.setNorm_dev_aj(txtNorm_dev_aj.getText());
		obj.setCod_fis_jur_aj(txtCod_fis_jur_aj.getText());
		obj.setNum_docfis_aj(txtNum_docfis_aj.getText());
		obj.setSerie_docfis_aj(txtSerie_docfis_aj.getText());
		obj.setSub_serie_docfis_aj(txtSub_Serie_docfis_aj.getText());
		obj.setCod_class_docfis_aj(txtCod_class_docfis_aj.getText());
		obj.setCod_docto_aj(txtCod_docto_aj.getText());
		obj.setCod_modelo_aj(txtCod_modelo_aj.getText());
		obj.setCod_modelo_cotepe_aj(txtCod_modelo_cotepe_aj.getText());
		obj.setNum_autentic_nfe_aj(txtNum_autentic_nfe_aj.getText());
		
		if (exception.getErrors().size() > 0) {
			throw exception;
		}

		return obj;
	}

	@FXML
	public void onBtCancelAction(ActionEvent event) {
		Utils.currentStage(event).close();
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		initializableNodes();
	}

	public void initializableNodes() {
		//Constraints.setTextFieldDouble(txtId);
		Constraints.setTextFieldMaxLength(txtCod_empresa_aj, 3);
		Constraints.setTextFieldMaxLength(txtCod_estab_aj, 6);
		Constraints.setTextFieldMaxLength(txtMovto_e_s_aj, 1);
		Constraints.setTextFieldMaxLength(txtNorm_dev_aj, 1);
		Constraints.setTextFieldMaxLength(txtCod_fis_jur_aj, 14);
		Constraints.setTextFieldMaxLength(txtNum_docfis_aj, 12);
		Constraints.setTextFieldMaxLength(txtSerie_docfis_aj, 3);
		Constraints.setTextFieldMaxLength(txtSub_Serie_docfis_aj, 2);
		Constraints.setTextFieldMaxLength(txtCod_class_docfis_aj, 2);
		Constraints.setTextFieldMaxLength(txtCod_docto_aj, 10);
		Constraints.setTextFieldMaxLength(txtCod_modelo_aj, 2);
		Constraints.setTextFieldMaxLength(txtCod_modelo_cotepe_aj, 2);
		Constraints.setTextFieldMaxLength(txtNum_autentic_nfe_aj, 44);
	}

	public void updateFormData() {
		if (entity == null) {
			throw new IllegalStateException("Entity was null");
		}
		txtCod_empresa.setText(entity.getCod_empresa());
		txtCod_estab.setText(entity.getCod_estab());
		if (entity.getData_fiscal() != null) {
			dpData_fiscal.setValue((LocalDateTime.ofInstant(entity.getData_fiscal().toInstant(), ZoneId.systemDefault()).toLocalDate()));
		}
		if (entity.getData_emissao() != null) {
			dpData_emissao.setValue((LocalDateTime.ofInstant(entity.getData_emissao().toInstant(), ZoneId.systemDefault()).toLocalDate()));
		}
		
		txtMovto_e_s.setText(entity.getMovto_e_s());
		txtNorm_dev.setText(entity.getNorm_dev());
		txtCod_fis_jur.setText(entity.getCod_fis_jur());
		txtNum_docfis.setText(entity.getNum_docfis());
		txtSerie_docfis.setText(entity.getSerie_docfis());
		txtSub_Serie_docfis.setText(entity.getSub_serie_docfis());

		txtCod_empresa_aj.setText(entity.getCod_empresa_aj());
		txtCod_estab_aj.setText(entity.getCod_estab_aj());
		if (entity.getData_fiscal_aj() != null) {
			dpData_fiscal_aj.setValue((LocalDateTime.ofInstant(entity.getData_fiscal_aj().toInstant(), ZoneId.systemDefault()).toLocalDate()));
		}
		if (entity.getData_emissao_aj() != null) {
			dpData_emissao_aj.setValue((LocalDateTime.ofInstant(entity.getData_emissao_aj().toInstant(), ZoneId.systemDefault()).toLocalDate()));
		}
		txtMovto_e_s_aj.setText(entity.getMovto_e_s_aj());
		txtNorm_dev_aj.setText(entity.getNorm_dev_aj());
		txtCod_fis_jur_aj.setText(entity.getCod_fis_jur_aj());
		txtNum_docfis_aj.setText(entity.getNum_docfis_aj());
		txtSerie_docfis_aj.setText(entity.getSerie_docfis_aj());
		txtSub_Serie_docfis_aj.setText(entity.getSub_serie_docfis_aj());
		txtCod_class_docfis_aj.setText(entity.getCod_class_docfis_aj());
		txtCod_docto_aj.setText(entity.getCod_docto_aj());
		txtCod_modelo_aj.setText(entity.getCod_modelo_aj());
		txtCod_modelo_cotepe_aj.setText(entity.getCod_modelo_cotepe_aj());
		txtNum_autentic_nfe_aj.setText(entity.getNum_autentic_nfe_aj());
		
	}
	
	private void setErrorMessages(Map<String, String> errors) {
		Set<String> fields = errors.keySet();
		
		if (fields.contains("name")) {
			//labelErrorName.setText(errors.get("name"));
		}
	}

}
