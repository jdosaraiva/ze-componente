package com.example.renderers;

import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.example.components.DocumentInputComponent;

import jakarta.faces.component.UIComponent;
import jakarta.faces.context.FacesContext;
import jakarta.faces.context.ResponseWriter;
import jakarta.faces.render.FacesRenderer;
import jakarta.faces.render.Renderer;

@FacesRenderer(componentFamily = "documentInput", rendererType = "documentInput")
public class DocumentInputRenderer extends Renderer {

    private static final Logger logger = LogManager.getLogger(DocumentInputRenderer.class);

    @Override
    public void decode(FacesContext context, UIComponent component) {
        logger.debug(String.format("Decodificando componente: {%s}", component.getClientId(context)));

        DocumentInputComponent documentInput = (DocumentInputComponent) component;
        String clientId = documentInput.getClientId(context);
        String submittedValue = context.getExternalContext()
                .getRequestParameterMap()
                .get(clientId);

        documentInput.setSubmittedValue(submittedValue);
        logger.debug(String.format("Valor submetido: {%s}", submittedValue));
    }

    @Override
    public void encodeBegin(FacesContext context, UIComponent component) throws IOException {
        if (!component.isRendered()) {
            logger.debug(String.format("Componente não está renderizado: {%s}", component.getClientId(context)));
            return;
        }

        DocumentInputComponent documentInput = (DocumentInputComponent) component;
        ResponseWriter writer = context.getResponseWriter();
        String clientId = documentInput.getClientId(context);
        
        logger.debug(String.format("Iniciando renderização do componente: {%s}", clientId));

        // Div container
        writer.startElement("div", component);
        writer.writeAttribute("class", "document-input-container", null);

        // Input element
        writer.startElement("input", component);
        writer.writeAttribute("id", clientId, null);
        writer.writeAttribute("name", clientId, null);
        writer.writeAttribute("type", "text", null);
        
        StringBuilder cssClasses = new StringBuilder("form-control document-input");
        if (!documentInput.isValid()) {
            cssClasses.append(" is-invalid");
        }
        writer.writeAttribute("class", cssClasses.toString(), null);

        if (documentInput.getValue() != null) {
            writer.writeAttribute("value", documentInput.getValue(), "value");
        }

        if (documentInput.isRequired()) {
            writer.writeAttribute("required", "required", null);
        }

        writer.endElement("input");

        // Script de máscara
        writer.startElement("script", null);
        writer.writeAttribute("type", "text/javascript", null);

        String script = 
            "jQuery(function() {\n" +
            "    var $input = jQuery('[id=\"" + clientId + "\"]');\n" +
            "    var currentMask = '000.000.000-00';\n" +
            "    \n" +
            "    function updateMask() {\n" +
            "        // Pega apenas os números do valor atual\n" +
            "        var numbersOnly = $input.val().replace(/\\D/g, '');\n" +
            "        \n" +
            "        // Determina qual máscara usar sem remover a atual\n" +
            "        var newMask = (numbersOnly.length > 11) ? '00.000.000/0000-00' : '000.000.000-00';\n" +
            "        \n" +
            "        // Só troca a máscara se for diferente da atual\n" +
            "        if (newMask !== currentMask) {\n" +
            "            currentMask = newMask;\n" +
            "            $input.unmask();\n" +
            "            $input.mask(currentMask);\n" +
            "        }\n" +
            "    }\n" +
            "    \n" +
            "    // Aplica a máscara inicial\n" +
            "    $input.mask(currentMask);\n" +
            "    \n" +
            "    // Monitora as mudanças\n" +
            "    $input.on('input', updateMask);\n" +
            "});\n";

        writer.writeText(script, null);
        writer.endElement("script");
        writer.endElement("div");

        logger.info(String.format("Renderização do componente concluída: {%s}", clientId));
    }
    
}

