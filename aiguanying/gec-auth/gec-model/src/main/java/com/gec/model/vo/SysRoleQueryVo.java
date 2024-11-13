//
//
package com.gec.model.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * <p>
 * 角色查询实体
 * </p>
 *
 * @author qy
 * @since 2019-11-08
 */
@Data
public class SysRoleQueryVo implements Serializable {


	private static final long serialVersionUID = 1L;
	
	private String roleName;

/*
    上面加了@Data就不用写
    public String getRoleName() {
		return roleName;
	}
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}*/
}

