package com.vaadin.customer.crud;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.crud.BinderCrudEditor;
import com.vaadin.flow.component.crud.Crud;
import com.vaadin.flow.component.crud.CrudEditor;
import com.vaadin.flow.component.crud.CrudI18n;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.orderedlayout.FlexComponent.Alignment;
import com.vaadin.flow.component.textfield.EmailField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.data.provider.ListDataProvider;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.PWA;

@Route("")
@PWA(name = "Almacen CRUD", shortName = "Almacen CRUD")

public class Almacen_CRUD extends VerticalLayout 
{

	public Almacen_CRUD() throws FileNotFoundException {
		setSizeFull();
        ListDataProvider<Producto> dataProvider = Carga_Datos();
        Crud<Producto> crud = new Crud<>(Producto.class, createGrid(), createDataProvider());
        crud.setMaxWidth("800px");
        crud.setWidth("100%");
        crud.setDataProvider(dataProvider);
        setHorizontalComponentAlignment(Alignment.CENTER, crud);

        CrudI18n customI18n = CrudI18n.createDefault();
        customI18n.setEditItem("Modificar Producto");
        customI18n.setNewItem("Nuevo Producto");
        crud.setI18n(customI18n);

        crud.addSaveListener(saveEvent -> {
            Producto toSave = saveEvent.getItem();
            // Guardar el producto en la Base de Datos
            if (!dataProvider.getItems().contains(toSave)) {
                dataProvider.getItems().add(toSave);
            }
            
        });

        crud.addDeleteListener(deleteEvent -> {
            // Borrar el producto en la Base de Datos
            dataProvider.getItems().remove(deleteEvent.getItem());
        });

        add(crud);
    }

	private ListDataProvider<Producto> Carga_Datos() throws FileNotFoundException 
	{
        List<Producto> data = new ArrayList<>();
 
        data.add(new Producto("648795213589","Manzana","2","Golden","5.50"));
        data.add(new Producto("589342785618","Platano","4","Canario","7.30"));
        
        return new ListDataProvider<>(data);
    }

	 private Grid<Producto> createGrid() {
	        Grid<Producto> grid = new Grid<>();
	        grid.addColumn(c -> c.getCodigo_barras()).setHeader("Código de barras").setWidth("160px");
	        grid.addColumn(c -> c.getNombre()).setHeader("Nombre");
	        grid.addColumn(c -> c.getCantidad()).setHeader("Cantidad");
	        grid.addColumn(c -> c.getMarca()).setHeader("Marca");
	        grid.addColumn(c -> c.getPrecio()).setHeader("Precio");
	        Crud.addEditColumn(grid);
	        return grid;
	    }

	 private CrudEditor<Producto> createDataProvider() 
	    {
	        TextField codigo_barras = new TextField("Código de barras");
	        setColspan(codigo_barras, 4);
	        codigo_barras.setRequiredIndicatorVisible(true);
	        
	        TextField nombre = new TextField("Nombre");
	        nombre.setRequiredIndicatorVisible(true);
	        setColspan(nombre, 2);
	        
	        TextField cantidad = new TextField("Cantidad");
	        setColspan(cantidad, 2);
	        cantidad.setRequiredIndicatorVisible(true);
	        
	        TextField marca = new TextField("Marca");
	        marca.setRequiredIndicatorVisible(true);
	        setColspan(marca, 2);
	        
	        TextField precio = new TextField("Precio");
	        setColspan(precio, 2);
	        precio.setRequiredIndicatorVisible(true);

	        FormLayout form = new FormLayout(codigo_barras, nombre, cantidad, marca, precio);
	        form.setResponsiveSteps(new FormLayout.ResponsiveStep("0", 4));

	        Binder<Producto> binder = new Binder<>(Producto.class);
	        binder.bind(codigo_barras, Producto::getCodigo_barras, Producto::setCodigo_barras);
	        binder.bind(nombre, Producto::getNombre, Producto::setNombre);
	        binder.bind(cantidad, Producto::getCantidad, Producto::setCantidad);
	        binder.bind(marca, Producto::getMarca, Producto::setMarca);
	        binder.bind(precio, Producto::getPrecio, Producto::setPrecio);

	        return new BinderCrudEditor<>(binder, form);
	    }

	 private void setColspan(Component component, int colspan) {
	        component.getElement().setAttribute("colspan", Integer.toString(colspan));
	    }
}
