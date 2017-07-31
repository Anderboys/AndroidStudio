package Beans;

/**
 * Created by Administrador on 31/10/2016.
 */
public class UsuarioBeans {

    private int id;
    private String nom,ape,direccion,Telefono,estado,dni,fecharegistro;

    public UsuarioBeans(int id, String nom, String ape, String direccion, String telefono, String estado, String dni, String fecharegistro) {
        this.id = id;
        this.nom = nom;
        this.ape = ape;
        this.direccion = direccion;
        Telefono = telefono;
        this.estado = estado;
        this.dni = dni;
        this.fecharegistro = fecharegistro;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getApe() {
        return ape;
    }

    public void setApe(String ape) {
        this.ape = ape;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getTelefono() {
        return Telefono;
    }

    public void setTelefono(String telefono) {
        Telefono = telefono;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getFecharegistro() {
        return fecharegistro;
    }

    public void setFecharegistro(String fecharegistro) {
        this.fecharegistro = fecharegistro;
    }


}
