package com.example.components;

import jakarta.faces.component.FacesComponent;
import jakarta.faces.component.UIOutput;

@FacesComponent("greeting")
public class GreetingComponent extends UIOutput {
    
    public GreetingComponent() {
        setRendererType("greeting");
    }

    @Override
    public String getFamily() {
        return "greeting";
    }
}
