// Node - generic nodes in block diagrams
package BD;
public class Node {

	private final Object node_object;
	private final int node_type, nt = 0;
	FunctionBlock Fbk;	

	public static final int SPtype = 1;
	public static final int FBtype = 2;
	public static final int IOtype = 3;

	public Node( Object no, int nt ) {
		node_object = no;
		node_type = nt;
		}

	public int NodeType() {
		return nt;
		}

	public Object getObject( ) {
		return node_object;
		}
	
}
