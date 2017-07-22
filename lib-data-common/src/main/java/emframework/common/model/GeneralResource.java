package emframework.common.model;

import java.lang.reflect.Field;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

import org.hibernate.annotations.GenericGenerator;

/**
 * The "general" resource, i.e. any entity or value object
 * Like address, user, etc.
 * The class is *ONLY* intent to be inherit by Value object and Resource(Entity) itself.
 */
@MappedSuperclass
public abstract class GeneralResource {
	@Id   
	@GenericGenerator(name = "uuid", strategy = "uuid2")
	@GeneratedValue(generator = "uuid")
	@Column(length=60)
	private String id;
	public String getId() {
        return id;
    }
    public void setId(String id){
    	this.id = id;
    }
    public GeneralResource(){}
    /**
     * 使用对象的新值来更新旧的对象的对应字段的数值。
     * 仅仅非空字段被更新
     * @param newVal
     * @throws IllegalArgumentException
     * @throws IllegalAccessException
     */
	public void updateWithNewValue(Object newVal) throws IllegalArgumentException, IllegalAccessException {
		if (this.getClass()!=newVal.getClass()) throw new IllegalArgumentException();
		for (Field f : this.getClass().getDeclaredFields()) {
			f.setAccessible(true);
			if (f.get(newVal) != null) {
				if (f.getDeclaredAnnotation(AcceptNewValue.class)!=null)
					f.set(this, f.get(newVal));
			}
		}
	}
}
