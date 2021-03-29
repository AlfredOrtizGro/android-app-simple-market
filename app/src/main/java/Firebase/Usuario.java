package Firebase;

public class Usuario
{
    String uid,nombre,correo,carrera;
    Float saldo,credito;

    public Usuario(String uid,String nombre, String correo, String carrera, Float saldo, Float credito)
    {
        this.uid = uid;
        this.nombre = nombre;
        this.correo = correo;
        this.carrera = carrera;
        this.saldo = saldo;
        this.credito = credito;
    }

    public String getNombre()
    {
        return nombre;
    }
    public String getUid()
    {
        return uid;
    }
    public void setUid(String uid)
    {
        this.uid = uid;
    }
    public void setNombre(String nombre)
    {
        this.nombre = nombre;
    }

    public String getCorreo()
    {
        return correo;
    }

    public void setCorreo(String correo)
    {
        this.correo = correo;
    }

    public String getCarrera()
    {
        return carrera;
    }

    public void setCarrera(String carrera)
    {
        this.carrera = carrera;
    }

    public Float getSaldo()
    {
        return saldo;
    }

    public void setSaldo(Float saldo)
    {
        this.saldo = saldo;
    }

    public Float getCredito()
    {
        return credito;
    }

    public void setCredito(Float credito)
    {
        this.credito = credito;
    }
}
