/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.com.smartcine.controladores;

import java.util.List;
import java.util.Map;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.persistence.Persistence;
import sv.com.smartcine.dao.FuncionesJpaController;
import sv.com.smartcine.entidades.Funciones;
import sv.com.smartcine.entidades.Peliculas;
import sv.com.smartcine.entidades.Salas;

@ManagedBean(name = "funciones")
@RequestScoped
public class ControladorFunciones {
    FuncionesJpaController funDAO;
    private Funciones fun;
    private Peliculas pelicula;
    private Salas sala;

    public ControladorFunciones() {
        funDAO = new FuncionesJpaController(Persistence.createEntityManagerFactory("SmartCinePU"));
        fun = new Funciones();
    }
    
    // Metodo para listar Funciones
    public List<Funciones> listar(){
        return funDAO.findFuncionesEntities();
    }
    
    // Metodo para ingresar Funciones
    public String ingresar(){
        funDAO.create(fun);
        return "listar?faces-redirect=true";
    }
    
    // Metodo para mapear Funciones
    public String editar(Funciones f){
        Map<String, Object> objetos = FacesContext.getCurrentInstance().getExternalContext().getSessionMap();
        objetos.put("fs", f);
        return "editar?faces-redirect=true"; 
    }
    
    // Metodo para editar Funciones
    public String actualizar(Funciones f){
        try {
            funDAO.edit(f);
            return "listar?faces-redirect=true";
        } catch (Exception e) {
            return null;
        }
    }
    
    // Metodo para eliminar Funciones
    public String destruir(Funciones f){
        try {
            funDAO.destroy(f.getId());
        return "listar?faces-redirect=true";
        } catch (Exception e) {
            return null;
        }
    }
    
    // Metodo para listar Funciones por Id Pelicula
    public List<Funciones> listarXIdPel(Integer id){ 
        return funDAO.porIdPel(id);     
    }
    
    // Metodo para listar Funciones por Id Sala
    public List<Funciones> listarXIdSal(Long id){ 
        return funDAO.porIdSala(id);
    }
    
    // Metodo para mapear Funciones 
    public String mapeoFun(Funciones f) {
        Map<String, Object> objetos = FacesContext.getCurrentInstance().getExternalContext().getSessionMap();
        objetos.put("sall", f);
        
        return "/faces/recursos/reserva/asientos?faces-redirect=true";
    }
    
    public Funciones getFun() {
        return fun;
    }

    public void setFun(Funciones fun) {
        this.fun = fun;
    }

    public Peliculas getPelicula() {
        return pelicula;
    }

    public void setPelicula(Peliculas pelicula) {
        this.pelicula = pelicula;
    }
    
    public Salas getSala() {
        return sala;
    }

    public void setSala(Salas sala) {
        this.sala = sala;
    }
    
    
}
