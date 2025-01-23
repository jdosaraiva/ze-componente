package com.example.components;

import java.util.regex.Pattern;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import jakarta.faces.application.FacesMessage;
import jakarta.faces.component.FacesComponent;
import jakarta.faces.component.UIInput;
import jakarta.faces.context.FacesContext;

/**
 * Componente personalizado para entrada de CPF/CNPJ que demonstra o ciclo de vida completo
 * de um componente JSF 4.0 com Jakarta EE 10.
 * 
 * O ciclo de vida de um componente JSF segue uma sequência específica de fases:
 * 1. Restore View
 * 2. Apply Request Values
 * 3. Process Validations
 * 4. Update Model Values
 * 5. Invoke Application
 * 6. Render Response
 * 
 * @author Seu Nome
 */
@FacesComponent("documentInput")
public class DocumentInputComponent extends UIInput {
    
    private static final Logger logger = LogManager.getLogger(DocumentInputComponent.class);
    private static final String CPF_PATTERN = "\\d{3}\\.\\d{3}\\.\\d{3}-\\d{2}";
    private static final String CNPJ_PATTERN = "\\d{2}\\.\\d{3}\\.\\d{3}/\\d{4}-\\d{2}";

    public DocumentInputComponent() {
        setRendererType("documentInput");  // Liga o componente ao renderer
    }

    @Override
    public String getFamily() {
    	return "documentInput";
    }
    
    // ========== Fase 1: Restore View ==========
    
    /**
     * Chamado quando o componente é criado pela primeira vez.
     * Parte da fase Restore View.
     */
    @Override
    public void restoreState(FacesContext context, Object state) {
        logger.info("1. RESTORE STATE: Restaurando estado do componente");
        Object[] values = (Object[]) state;
        super.restoreState(context, values[0]);
        // Aqui você restauraria propriedades customizadas se necessário
        logger.debug("Estado restaurado com sucesso");
    }

    /**
     * Chamado para salvar o estado do componente entre requisições.
     * Parte da fase Restore View.
     */
    @Override
    public Object saveState(FacesContext context) {
        logger.info("1. SAVE STATE: Salvando estado do componente");
        Object[] values = new Object[1];
        values[0] = super.saveState(context);
        // Aqui você salvaria propriedades customizadas se necessário
        logger.info("Estado salvo com sucesso");
        return values;
    }

    // ========== Fase 2: Apply Request Values ==========

    /**
     * Chamado durante a fase Apply Request Values para processar
     * os parâmetros da requisição.
     */
    @Override
    public void decode(FacesContext context) {
        logger.info("2. DECODE: Processando valores da requisição");
        if (context == null) {
            throw new NullPointerException();
        }
        
        String clientId = getClientId(context);
        String submittedValue = context.getExternalContext()
                                     .getRequestParameterMap()
                                     .get(clientId);
        
        if (submittedValue != null) {
            setSubmittedValue(submittedValue);
            logger.info(String.format("Valor submetido: {%s}", submittedValue));
        }
        
        setValid(true);  // Reset validation flag
    }

    // ========== Fase 3: Process Validations ==========

    /**
     * Processa as validações do componente.
     * Parte da fase Process Validations.
     */
    @Override
    protected void validateValue(FacesContext context, Object value) {
        logger.info("3. VALIDATE: Validando valor do componente");
        
        if (value == null || value.toString().trim().isEmpty()) {
            if (isRequired()) {
                logger.warn("Validação falhou: valor obrigatório não fornecido");
                setValid(false);
                context.addMessage(getClientId(context),
                    new FacesMessage(FacesMessage.SEVERITY_ERROR,
                        "Campo obrigatório",
                        "Por favor, insira um CPF ou CNPJ"));
            }
            return;
        }
        
        String doc = value.toString();
        if (!Pattern.matches(CPF_PATTERN, doc) && !Pattern.matches(CNPJ_PATTERN, doc)) {
            logger.warn(String.format("Validação falhou: formato inválido - {%s}", doc));
            setValid(false);
            context.addMessage(getClientId(context),
                new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    "Formato inválido",
                    "Use o formato XXX.XXX.XXX-XX para CPF ou XX.XXX.XXX/XXXX-XX para CNPJ"));
        } else {
            logger.info(String.format("Validação bem-sucedida para o valor: {%s}", doc));
        }
    }

    // ========== Fase 4: Update Model Values ==========

    /**
     * Atualiza o modelo com o valor validado.
     * Parte da fase Update Model Values.
     */
    @Override
    public void updateModel(FacesContext context) {
        logger.info("4. UPDATE MODEL: Atualizando modelo com valor validado");
        super.updateModel(context);
    }

    // ========== Fase 5: Invoke Application ==========
    // Esta fase é geralmente manipulada pelo backing bean, não pelo componente

    // ========== Fase 6: Render Response ==========

    /**
     * Chamado antes do início da renderização.
     * Parte da fase Render Response.
     */
    @Override
    public void encodeBegin(FacesContext context) throws java.io.IOException {
        logger.info("6. ENCODE BEGIN: Iniciando renderização do componente");
        if (!isRendered()) {
            logger.debug("Componente não está marcado para renderização");
            return;
        }
        super.encodeBegin(context);
    }

    /**
     * Chamado após encodeBegin para renderizar o conteúdo do componente.
     * Parte da fase Render Response.
     */
    @Override
    public void encodeChildren(FacesContext context) throws java.io.IOException {
        logger.info("6. ENCODE CHILDREN: Renderizando filhos do componente");
        // Este método não é necessário pois não temos componentes filhos
        super.encodeChildren(context);
    }

    /**
     * Chamado após encodeChildren para finalizar a renderização.
     * Parte da fase Render Response.
     */
    @Override
    public void encodeEnd(FacesContext context) throws java.io.IOException {
        logger.info("6. ENCODE END: Finalizando renderização do componente");
        super.encodeEnd(context);
    }
    
    @Override
    public void processUpdates(FacesContext context) {
    	super.processUpdates(context);
    }
}
