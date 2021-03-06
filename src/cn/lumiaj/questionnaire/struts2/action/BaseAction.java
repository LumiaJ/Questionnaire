package cn.lumiaj.questionnaire.struts2.action;

import java.lang.reflect.ParameterizedType;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.Preparable;

public abstract class BaseAction<T> extends ActionSupport implements ModelDriven<T>, Preparable {
	private static final long serialVersionUID = 1L;
	protected T model;

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public BaseAction() {
		try {
			ParameterizedType type = (ParameterizedType) this.getClass().getGenericSuperclass();
			Class clazz = (Class<T>) type.getActualTypeArguments()[0];
			model = (T) clazz.newInstance();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void prepare() throws Exception {
	}

	@Override
	public T getModel() {
		return model;
	}

}
