package manajemen.karyawan.manajemen_karyawan.entitiy;
import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

@Entity
@Table(name = "employees")
public class EmployeeEntity {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
    
    @NotBlank(message = "Nama tidak boleh kosong")
    private String name;

    @NotBlank(message = "Email tidak boleh kosong")
    @Column(unique = true, nullable=false)
    private String email;

    @NotBlank(message = "Posisi tidak boleh kosong")
    private String position;

    @NotNull(message = "Gaji tidak boleh kosong")
    @Positive(message = "Gaji harus lebih besar dari 0")
    private Double salary;

    @CreationTimestamp
    private LocalDateTime created_at;

    @UpdateTimestamp
    private LocalDateTime updated_at;

    public Long getId(){
        return id;
    }

    public void setId(Long id){
        this.id = id;
    }

    public String getName(){
        return name;
    }

    public void setName(String name){
        this.name = name;
    }

    public String getEmail(){
        return email;
    }

    public void setEmail(String email){
        this.email = email;
    }

    public String getPosition(){
        return position;
    }

    public void setPosition(String position){
        this.position = position;
    }

    public Double getSalary(){
        return salary;
    }

    public void setSalary(Double salary){
        this.salary = salary;
    }

    public LocalDateTime getCreatedAt(){
        return created_at;
    }

    public void setCreatedAt(LocalDateTime created_at){
        this.created_at = created_at;
    }

    public LocalDateTime getUpdatedAt(){
        return updated_at;
    }

    public void setUpdatedAt(LocalDateTime updated_at){
        this.updated_at = updated_at;
    }
}
