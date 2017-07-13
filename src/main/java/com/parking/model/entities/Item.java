package com.parking.model.entities;

import java.io.Serializable;

public class Item<T extends Item<T>> implements Serializable {

	private Long id;

	private Integer enabled;

    public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getEnabled() {
		return enabled;
	}

	public void setEnabled(Integer enabled) {
		this.enabled = enabled;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		T item = (T) o;

		return getId() != null ? getId().equals(item.getId()) : item.getId() == null;
	}
}