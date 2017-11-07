package com.iscas.sdas.tag;

import java.io.IOException;

import javax.servlet.jsp.JspContext;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.SimpleTagSupport;

import com.iscas.sdas.util.CommonUntils;
import com.iscas.sdas.util.Constraints;
import com.iscas.sdas.util.Message;

public class ButtonTag extends SimpleTagSupport implements ButtonAuthority{

	private String type;
	private String id;
	private String name;
	private String title;
	private String css;
	private String onclick;
	private String icon;
	private String auth;
	private String button;
	private JspContext jspContext;
	private String iconClass;
	private static final String html = "<button id=\"{0}\" type=\"{1}\" class=\"{2}\" onclick=\"{3}\"><span class=\"{4}\" aria-hidden=\"true\"></span>{5}</button>";

	public ButtonTag() {
		button = "button";
		iconClass = "glyphicon glyphicon-";
	}

	@Override
	public void doTag() throws JspException, IOException {
		label0: {
			if (CommonUntils.isempty(type)) {
				type = "search";
			}
			String s;
			switch ((s = type).hashCode()) {
			default:
				break;

			case -1367724212:
				if (!s.equals("cancle")) {
					break;
				}
				auth = "cancle";
				id = CommonUntils.isempty(id) ? "btn_cancle" : id;
				name = CommonUntils.isempty(name) ? "btn_cancle_name" : name;
				title = CommonUntils.isempty(title) ? Constraints.BTN_CANCLE : title;
				onclick = CommonUntils.isempty(onclick) ? "closemodal()" : onclick;
				css = CommonUntils.isempty(css) ? "btn btn-primary" : css;
				icon = (new StringBuilder(String.valueOf(iconClass)))
						.append(CommonUntils.isempty(icon) ? "remove-circle" : icon).toString();
				button = "button";
				break label0;

			case -1349088399:
				if (s.equals("custom"))
					;
				break;

			case -1335458389:
				if (!s.equals("delete")) {
					break;
				}
				auth = "btnDelete";
				id = CommonUntils.isempty(id) ? "btn_delete" : id;
				name = CommonUntils.isempty(name) ? "btn_delete_name" : name;
				title = CommonUntils.isempty(title) ? Constraints.BTN_DELETE : title;
				onclick = CommonUntils.isempty(onclick) ? "deleteRow()" : onclick;
				css = CommonUntils.isempty(css) ? "btn btn-outline btn-default" : css;
				icon = (new StringBuilder(String.valueOf(iconClass)))
						.append(CommonUntils.isempty(icon) ? "remove" : icon).toString();
				button = "button";
				break label0;

			case -1289153612:
				if (!s.equals("export")) {
					break;
				}
				auth = "export";
				id = CommonUntils.isempty(id) ? "btn_export" : id;
				name = CommonUntils.isempty(name) ? "btn_export_name" : name;
				title = CommonUntils.isempty(title) ? Constraints.BTN_EXPORT : title;
				onclick = CommonUntils.isempty(onclick) ? "export()" : onclick;
				css = CommonUntils.isempty(css) ? "btn btn-outline btn-default" : css;
				icon = (new StringBuilder(String.valueOf(iconClass)))
						.append(CommonUntils.isempty(icon) ? "export" : icon).toString();
				button = "button";
				break label0;

			case -1184795739:
				if (!s.equals("import")) {
					break;
				}
				auth = "import";
				id = CommonUntils.isempty(id) ? "btn_import" : id;
				name = CommonUntils.isempty(name) ? "btn_import_name" : name;
				title = CommonUntils.isempty(title) ? Constraints.BTN_IMPORT : title;
				onclick = CommonUntils.isempty(onclick) ? "import()" : onclick;
				css = CommonUntils.isempty(css) ? "btn btn-outline btn-default" : css;
				icon = (new StringBuilder(String.valueOf(iconClass)))
						.append(CommonUntils.isempty(icon) ? "import" : icon).toString();
				button = "button";
				break label0;

			case -1183792455:
				if (!s.equals("insert")) {
					break;
				}
				auth = "btnAdd";
				id = CommonUntils.isempty(id) ? "btn_add" : id;
				name = CommonUntils.isempty(name) ? "btn_insert_name" : name;
				title = CommonUntils.isempty(title) ? Constraints.BTN_INSERT : title;
				onclick = CommonUntils.isempty(onclick) ? "addNew()" : onclick;
				css = CommonUntils.isempty(css) ? "btn btn-outline btn-default" : css;
				icon = (new StringBuilder(String.valueOf(iconClass))).append(CommonUntils.isempty(icon) ? "plus" : icon)
						.toString();
				button = "button";
				break label0;

			case -906336856:
				if (!s.equals("search")) {
					break;
				}
				auth = "search";
				id = CommonUntils.isempty(id) ? "btn_search" : id;
				name = CommonUntils.isempty(name) ? "btn_search_name" : name;
				title = CommonUntils.isempty(title) ? Constraints.BTN_SEARCH : title;
				onclick = CommonUntils.isempty(onclick) ? "searchInfo()" : onclick;
				css = CommonUntils.isempty(css) ? "btn btn-primary" : css;
				icon = (new StringBuilder(String.valueOf(iconClass)))
						.append(CommonUntils.isempty(icon) ? "search" : icon).toString();
				button = "button";
				break label0;

			case -838846263:
				if (!s.equals("update")) {
					break;
				}
				auth = "btnEdit";
				id = CommonUntils.isempty(id) ? "btn_update" : id;
				name = CommonUntils.isempty(name) ? "btn_update_name" : name;
				title = CommonUntils.isempty(title) ? Constraints.BTN_UPDATE : title;
				onclick = CommonUntils.isempty(onclick) ? "editRow()" : onclick;
				css = CommonUntils.isempty(css) ? "btn btn-outline btn-default" : css;
				icon = (new StringBuilder(String.valueOf(iconClass)))
						.append(CommonUntils.isempty(icon) ? "pencil" : icon).toString();
				button = "button";
				break label0;

			case 3108362:
				if (!s.equals("edit")) {
					break;
				}
				auth = "btnEditSave";
				id = CommonUntils.isempty(id) ? "btn_edit" : id;
				name = CommonUntils.isempty(name) ? "btn_edit_name" : name;
				title = CommonUntils.isempty(title) ? Constraints.BTN_EDIT : title;
				onclick = CommonUntils.isempty(onclick) ? "" : onclick;
				css = CommonUntils.isempty(css) ? "btn btn-primary" : css;
				icon = (new StringBuilder(String.valueOf(iconClass))).append(CommonUntils.isempty(icon) ? "edit" : icon)
						.toString();
				button = "submit";
				break label0;

			case 3522941:
				if (!s.equals("save")) {
					break;
				}
				auth = "btnAddSave";
				id = CommonUntils.isempty(id) ? "btn_save" : id;
				name = CommonUntils.isempty(name) ? "btn_save_name" : name;
				title = CommonUntils.isempty(title) ? Constraints.BTN_SAVE : title;
				onclick = CommonUntils.isempty(onclick) ? "" : onclick;
				css = CommonUntils.isempty(css) ? "btn btn-primary" : css;
				icon = (new StringBuilder(String.valueOf(iconClass))).append(CommonUntils.isempty(icon) ? "save" : icon)
						.toString();
				button = "submit";
				break label0;
			}
			auth = CommonUntils.isempty(auth) ? "btnCustom" : auth;
			id = CommonUntils.isempty(id) ? "btn_custom" : id;
			name = CommonUntils.isempty(name) ? "btn_custom_name" : name;
			title = CommonUntils.isempty(title) ? Constraints.BTN_CUSTOM : title;
			onclick = CommonUntils.isempty(onclick) ? "custom()" : onclick;
			css = CommonUntils.isempty(css) ? "btn btn-outline btn-default" : css;
			icon = CommonUntils.isempty(icon) ? "glyphicon glyphicon-cog"
					: icon.contains("glyphicon glyphicon-") ? icon
							: (new StringBuilder(String.valueOf(iconClass))).append(icon).toString();
			button = "button";
		}
		if ("insert".equals(type) || "delete".equals(type)) {
			if (hasAuthority()) {
				JspWriter out = getJspContext().getOut();
				out.println(Message.format(
						"<button id=\"{0}\" type=\"{1}\" class=\"{2}\" onclick=\"{3}\"><span class=\"{4}\" aria-hidden=\"true\"></span>{5}</button>",
						new Object[] { id, button, css, onclick, icon, title }));
			}
		}else {
			JspWriter out = getJspContext().getOut();
			out.println(Message.format(
					"<button id=\"{0}\" type=\"{1}\" class=\"{2}\" onclick=\"{3}\"><span class=\"{4}\" aria-hidden=\"true\"></span>{5}</button>",
					new Object[] { id, button, css, onclick, icon, title }));
		}		
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getCss() {
		return css;
	}

	public void setCss(String css) {
		this.css = css;
	}

	public String getOnclick() {
		return onclick;
	}

	public void setOnclick(String onclick) {
		this.onclick = onclick;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public String getAuth() {
		return auth;
	}

	public void setAuth(String auth) {
		this.auth = auth;
	}

	@Override
	public boolean hasAuthority() {
		jspContext = super.getJspContext();
		String roleName = (String) jspContext.getAttribute("role");
		if (Constraints.ROLE_ADMIN.equals(roleName)) {
			return true;
		}else {
			return false;
		}
	}

	
	
	

}
