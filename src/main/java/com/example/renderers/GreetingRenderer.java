package com.example.renderers;

import jakarta.faces.component.UIComponent;
import jakarta.faces.context.FacesContext;
import jakarta.faces.context.ResponseWriter;
import jakarta.faces.render.FacesRenderer;
import jakarta.faces.render.Renderer;
import java.io.IOException;

@FacesRenderer(
    componentFamily = "greeting",
    rendererType = "greeting"
)
public class GreetingRenderer extends Renderer {

    @Override
    public void encodeBegin(FacesContext context, UIComponent component) 
            throws IOException {
        
        ResponseWriter writer = context.getResponseWriter();
        String name = (String) component.getAttributes().get("name");
        String style = (String) component.getAttributes().get("style");
        String styleClass = (String) component.getAttributes().get("styleClass");
        
        writer.startElement("div", component);
        writer.writeAttribute("id", component.getClientId(context), null);
        
        if (style != null) {
            writer.writeAttribute("style", style, null);
        }
        
        if (styleClass != null) {
            writer.writeAttribute("class", styleClass, null);
        }
        
        writer.startElement("span", component);
        writer.write("Olá, " + (name != null ? name : "Visitante") + "!");
        writer.endElement("span");
    }

    @Override
    public void encodeEnd(FacesContext context, UIComponent component) 
            throws IOException {
        ResponseWriter writer = context.getResponseWriter();
        writer.endElement("div");
    }
}
