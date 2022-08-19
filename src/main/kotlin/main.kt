fun main() {
    val graph: Graph = Graph.createGraph()
    println(Graph.depthFirstTraversal(graph, "v1"))
    println(Graph.recursiveDepth(graph, "v1"))
    println(Graph.breadthFirstTraversal(graph, "v1"))
}