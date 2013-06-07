package com.bytepoxic.core.web.faces.bean.common;

import java.io.Serializable;
import java.util.List;

public interface BreadCrumbBean extends Serializable {
	List<BreadCrumb> getBreadCrumbs(String url);

	BreadCrumb getRootBreadCrumb();

	boolean isUseRootWhenUnfound();
}
