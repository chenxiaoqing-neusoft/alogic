package com.alogic.together.idu;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import com.alogic.xscript.ExecuteWatcher;
import com.alogic.xscript.Logiclet;
import com.alogic.xscript.LogicletContext;
import com.alogic.xscript.doc.XsObject;
import com.anysoft.util.Properties;
import com.anysoft.util.PropertiesConstants;
import com.logicbus.dbcp.processor.Preprocessor;
import com.logicbus.dbcp.sql.DBTools;

/**
 * Delete
 * @author yyduan
 *
 * @version 1.6.8.14 [20170509 duanyy] <br>
 * - 增加xscript的中间文档模型,以便支持多种报文协议 <br>
 *
 */
public class Delete extends DBOperation{
	protected String sqlDelete = "";	
	protected Preprocessor processor = null;
	
	public Delete(String tag, Logiclet p) {
		super(tag, p);
	}
	
	@Override
	public void configure(Properties p){
		super.configure(p);
		sqlDelete = PropertiesConstants.getString(p, "sql.Delete", sqlDelete);
		processor = new Preprocessor(sqlDelete);
	}

	@Override
	protected void onExecute(Connection conn,XsObject root,XsObject current, LogicletContext ctx,
			ExecuteWatcher watcher) {
		List<Object> data = new ArrayList<Object>();
		String sql = processor.process(ctx, data);
		DBTools.delete(conn, sql, data.toArray());
	}

}
