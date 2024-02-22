data class Node(val value: Int, val left: Node? = null, val right: Node? = null)

class ImmutableBinaryTree(private val root: Node? = null) {

    fun insert(value: Int): ImmutableBinaryTree {
        return ImmutableBinaryTree(insertNode(root, value))
    }

    fun search(value: Int): Boolean {
        return searchNode(root, value) != null
    }

    fun delete(value: Int): ImmutableBinaryTree {
        return ImmutableBinaryTree(deleteNode(root, value))
    }

    fun traverseInOrder(): List<Int> {
        val result = mutableListOf<Int>()
        traverseInOrder(root, result)
        return result
    }

    private fun insertNode(node: Node?, value: Int): Node {
        if (node == null) {
            return Node(value)
        }

        return if (value < node.value) {
            Node(node.value, insertNode(node.left, value), node.right)
        } else {
            Node(node.value, node.left, insertNode(node.right, value))
        }
    }

    private fun searchNode(node: Node?, value: Int): Node? {
        if (node == null || node.value == value) {
            return node
        }

        return if (value < node.value) {
            searchNode(node.left, value)
        } else {
            searchNode(node.right, value)
        }
    }

    private fun deleteNode(node: Node?, value: Int): Node? {
        if (node == null) {
            return null
        }

        return if (value == node.value) {
            if (node.left == null) {
                node.right
            } else if (node.right == null) {
                node.left
            } else {
                val min = findMin(node.right)
                Node(min.value, node.left, deleteNode(node.right, min.value))
            }
        } else if (value < node.value) {
            Node(node.value, deleteNode(node.left, value), node.right)
        } else {
            Node(node.value, node.left, deleteNode(node.right, value))
        }
    }

    private fun findMin(node: Node): Node {
        var current = node
        while (current.left != null) {
            current = current.left!!
        }
        return current
    }

    private fun traverseInOrder(node: Node?, result: MutableList<Int>) {
        if (node != null) {
            traverseInOrder(node.left, result)
            result.add(node.value)
            traverseInOrder(node.right, result)
        }
    }
}

fun main() {
    val tree = ImmutableBinaryTree()
        .insert(5)
        .insert(3)
        .insert(7)
        .insert(2)
        .insert(4)

    println("Search 4: ${tree.search(4)}")
    println("Search 6: ${tree.search(6)}")

    val updatedTree = tree.delete(3)
    println("Tree after deleting 3: ${updatedTree.traverseInOrder()}") // [2, 4, 5, 7]
}