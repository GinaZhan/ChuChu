import random
class RBNode:

    def __init__(self, value):
        self.value = value
        self.left = None
        self.right = None
        self.parent = None
        self.colour = "R"

    def get_uncle(self):
        return

    def is_leaf(self):
        return self.left == None and self.right == None

    def is_left_child(self):
        return self == self.parent.left

    def is_right_child(self):
        return not self.is_left_child()

    def is_red(self):
        return self.colour == "R"

    def is_black(self):
        return not self.is_red()

    def make_black(self):
        self.colour = "B"

    def make_red(self):
        self.colour = "R"

    def get_brother(self):
        if self.parent.right == self:
            return self.parent.left
        return self.parent.right

    def get_uncle(self):
        return self.parent.get_brother()

    def uncle_is_black(self):
        if self.get_uncle() == None:
            return True
        return self.get_uncle().is_black()

    def __str__(self):
        return "(" + str(self.value) + "," + self.colour + ")"

    def __repr__(self):
         return "(" + str(self.value) + "," + self.colour + ")"

    def rotate_right(self):
        p = self.parent
        if p.parent == None:
            self.parent = p.parent
        elif p.is_left_child():
            self.parent = p.parent
            p.parent.left = self
        else:
            self.parent = p.parent
            p.parent.right = self
        p.left = self.right
        if self.right != None:
            self.right.parent = p
        self.right = p
        p.parent = self
        self.colour = p.colour
        p.colour = "R"

    def rotate_left(self):
        p = self.parent
        if p.parent == None:
            self.parent = p.parent
        elif p.is_left_child():
            self.parent = p.parent
            p.parent.left = self
        else:
            self.parent = p.parent
            p.parent.right = self
        p.right = self.left
        if self.left != None:
            self.left.parent = p
        self.left = p
        p.parent = self
        self.colour = p.colour
        p.colour = "R"




class RBTree:

    def __init__(self):
        self.root = None

    def is_empty(self):
        return self.root == None

    def get_height(self):
        if self.is_empty():
            return 0
        return self.__get_height(self.root)

    def __get_height(self, node):
        if node == None:
            return 0
        return 1 + max(self.__get_height(node.left), self.__get_height(node.right))

    def insert(self, value):
        if self.is_empty():
            self.root = RBNode(value)
            self.root.make_black()
        else:
            self.__insert(self.root, value)

    def __insert(self, node, value):
        if value < node.value:
            if node.left == None:
                node.left = RBNode(value)
                node.left.parent = node
                self.fix(node.left)
            else:
                self.__insert(node.left, value)
        else:
            if node.right == None:
                node.right = RBNode(value)
                node.right.parent = node
                self.fix(node.right)
            else:
                self.__insert(node.right, value)

    def fix(self, node):
        #You may alter code in this method if you wish, it's merely a guide.
        
        # while node != None and node.parent != None and node.parent.is_red(): 
        while node != None and node.is_red():
            if node.parent == None:
                node.make_black()
                break
            elif node.parent.is_black() and (node.get_brother() == None or node.get_brother().is_black()):
                if node.is_right_child():
                    node.rotate_left()
                    if self.root == node.left:
                        self.root = node
                    break
                else:
                    break
            elif node.is_left_child() and node.parent.is_red():
                node.parent.rotate_right()
                if self.root == node.get_brother():
                    self.root = node.parent
                node.make_black()
                node.get_brother().make_black()
                node.parent.make_red()
                node = node.parent
            elif node.is_right_child() and node.parent.is_red():
                node.rotate_left()
                node.rotate_right()
                if self.root == node.right:
                    self.root = node
                node.left.make_black()
                node.right.make_black()
                node.make_red()
            elif node.get_brother() != None and node.get_brother().is_red():
                node.make_black()
                node.get_brother().make_black()
                node.parent.make_red()
                node = node.parent
            else:
                break
                    
        
    def __str__(self):
        if self.is_empty():
            return "[]"
        return "[" + self.__str_helper(self.root) + "]"

    def __str_helper(self, node):
        if node.is_leaf():
            return "[" + str(node) + "]"
        if node.left == None:
            return "[" + str(node) + " -> " + self.__str_helper(node.right) + "]"
        if node.right == None:
            return "[" +  self.__str_helper(node.left) + " <- " + str(node) + "]"
        return "[" + self.__str_helper(node.left) + " <- " + str(node) + " -> " + self.__str_helper(node.right) + "]"

Tree = RBTree()
Tree.insert(100)
Tree.insert(6)
Tree.insert(1)
Tree.insert(9)
Tree.insert(4)
Tree.insert(10)
Tree.insert(2)
print(Tree)

# l = RBNode(6)
# m = RBNode(5)
# s = RBNode(3)
# s.parent = l
# l.left = s
# s.colour = "R"
# l.colour = "B"
# s.right = l
# s.colour = "B"
# l.parent = s
# print(s)
# print(l)
# print(l.parent)
# print(s.parent)
# l.rotate_left()
# print(l.parent)
# print(s.parent)
# print(l.colour)
# print(s.colour)

# s.rotate_right()
# # print(s)
# # print(l)
# print(l.parent)
# l.rotate_left()
# print(l.parent)
# print(s)
# print(l)

# def create_random_list(n):
#     L = []
#     for _ in range(n):
#         L.append(random.randint(1, n))
#     return L

# T = RBTree()
# for _ in range(1):
#     L = create_random_list(10000)
#     for x in L:
#         T.insert(x)

T = RBTree()
for _ in range(1):
    for x in range(1,10):
        T.insert(x)

# T2 = RBTree()
# for i in range(10000, 0, -1):
#     T2.insert(i)
# T3 = RBTree()
# for i in range(1, 11):
#     T3.insert(i)
# print(T)
# print(T.get_height())
# print(T2.get_height())
# print(T3.get_height())
# print(T3)