package com.fun.orm.genxml.creator;

import java.util.List;

import com.fun.orm.retrieve.ModelInfoHolder;
import com.fun.orm.retrieve.bean.ModelProperty;
import com.fun.orm.retrieve.bean.PrimayProperty;
import com.fun.orm.retrieve.bean.ReferencedProperty;
import com.fun.orm.utils.Constants;
import com.fun.orm.utils.NameUtils;

public class UpdateCreator implements MethodCreator {

	@Override
	public void create(StringBuffer sb, ModelInfoHolder mih) {
		sb.append(createUpdate(mih));
		sb.append(createUpdateBatch(mih));
	}
	
	/**
	 * 创建update方法
	 * @param mih
	 * @return
	 */
	private String createUpdate(ModelInfoHolder mih) {
		StringBuffer sb = new StringBuffer();
		sb.append("\t<update id=\"");
		sb.append(Constants.SQLID_UPDATE + "\" parameterType=\"");
		sb.append(mih.getClassName());
		sb.append("\">\r\n");
		sb.append("\t\tupdate ");
		sb.append(mih.getTableName() + "\r\n");
		sb.append(getSetAndCondition(mih.getPropertyList(), ""));
		sb.append("\t</update>\r\n\r\n");
		return sb.toString();
	}
	
	/**
	 * 创建updateBatch方法
	 * @param mih
	 * @return
	 */
	private String createUpdateBatch(ModelInfoHolder mih) {
		String modelName = NameUtils.getLowerCaseClassName(NameUtils.getShortClassName(mih.getClassName()));
		StringBuffer sb = new StringBuffer();
		sb.append("\t<update id=\"");
		sb.append(Constants.SQLID_UPDATE_BATCH + "\" parameterType=\"java.util.List");
		sb.append("\">\r\n");
		sb.append("\t\t<foreach collection=\"list\" item=\"");
		sb.append(modelName);
		sb.append("\" separator=\";\">\r\n");
		sb.append("\t\tupdate ");
		sb.append(mih.getTableName() + "\r\n");
		sb.append(getSetAndCondition(mih.getPropertyList(), modelName + "."));
		sb.append("\t\t</foreach>\r\n");
		sb.append("\t</update>\r\n\r\n");
		return sb.toString();
	}
	
	/**
	 * 获取set和where
	 * @param mps
	 * @return
	 */
	private  String getSetAndCondition(List<ModelProperty> mps, String itemPrefix) {
		StringBuffer sb = new StringBuffer();
		String idCondition = null;
		sb.append("\t\t<trim suffixOverrides=\",\">\r\n");
		sb.append("\t\t<set>\r\n");
		for(ModelProperty mp : mps) {
			if(mp instanceof PrimayProperty) {
				PrimayProperty pp = (PrimayProperty)mp;
				idCondition = pp.getTableColumn() + "=#{" + itemPrefix + pp.getModelProperty() + "}";
				continue;
			}
			String property = mp.getModelProperty();
			String colName = mp.getTableColumn();
			sb.append("\t\t\t<if test=\"");
			if(mp instanceof ReferencedProperty) {
				ReferencedProperty rp = (ReferencedProperty)mp;
				String refName = NameUtils.getLowerCaseClassName(rp.getJavaType());
				property = refName  + "." + NameUtils.underline2Camel(rp.getReferencedColumn(), true);
				sb.append(itemPrefix + refName);
				sb.append(" != null and ");
			}
			property = itemPrefix + property;
			sb.append(property);
			sb.append(" != null\">");
			sb.append(colName);
			sb.append("=#{");
			sb.append(property);
			sb.append("},</if>\r\n");
		}
		sb.append("\t\t</set>\r\n");
		sb.append("\t\t</trim>\r\n");
		sb.append("\t\twhere ");
		sb.append(idCondition);
		sb.append("\r\n");
		return sb.toString();
	}
}
