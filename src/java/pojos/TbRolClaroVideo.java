
package pojos;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="TB_ROL_CLARO_VIDEO")
public class TbRolClaroVideo {
    
    
     private long idRol;
     private String descripcion;

    
    public TbRolClaroVideo(){
    
    
    }
    
    public TbRolClaroVideo(long idRol, String descripcion){
    
    
    }
   
    @Id 
    @Column(name="ID_ROL")
    public long getIdRol() {
        return idRol;
    }

   
    public void setIdRol(long idRol) {
        this.idRol = idRol;
    }

    @Column(name="DESCRIPCION", length=150)
    public String getDescripcion() {
        return descripcion;
    }

    
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    
}
