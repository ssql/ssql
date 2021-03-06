package supersql.codegenerator.HTML_Flexbox;

import org.jsoup.nodes.Element;
import org.jsoup.parser.Tag;

import supersql.codegenerator.Attribute;
import supersql.codegenerator.Connector;
import supersql.codegenerator.Function;
import supersql.codegenerator.IfCondition;
import supersql.codegenerator.TFE;
import supersql.extendclass.ExtList;

public class HTML_FlexboxIfCondition extends IfCondition {

	public HTML_FlexboxIfCondition(Attribute condition, TFE thenTfe, TFE elseTfe){
		super(condition, thenTfe, elseTfe);
	}
	
	@Override
	public Element createNode(ExtList data_info){
		String conditionResult = (String) data_info.get(0);
		if((((conditionResult)).toString()).equals("t")){
			if(thenTfe instanceof Connector || thenTfe instanceof Attribute
				|| thenTfe instanceof Function || thenTfe instanceof IfCondition)
				return (Element) thenTfe.createNode(data_info.ExtsubList(1, thenTfe.countconnectitem()+1));
			else
				return (Element) thenTfe.createNode((ExtList)data_info.get(1));
		}
		else if(elseTfe != null){
			int from = thenTfe.countconnectitem()+1;
			if(elseTfe instanceof Connector || elseTfe instanceof Attribute
					|| elseTfe instanceof Function || elseTfe instanceof IfCondition)
				return (Element) elseTfe.createNode(data_info.ExtsubList(from, data_info.size()));
			else
				return (Element) elseTfe.createNode((ExtList)data_info.get(from));		
		}
		return new Element(Tag.valueOf("span"), "");
	}
}
