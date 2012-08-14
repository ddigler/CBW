class Holder:
    def __init__(self):
        self._nodes = {}

    def __getattr__(self, name):
        if name.startswith('_'):
            raise AttributeError
        if name == 'nodes':
            return self._nodes
        if not self.nodes.has_key(name):
            self.nodes[name] = Holder()
        return self.nodes[name]

    def __setattr__(self, k, v):
        if not k.startswith("_"):
            self._nodes[k] = v
        else:
            self.__dict__[k] = v

root = Holder()

def h(name):
    if type(name) != str:
        raise Exception('Can only use strings to instantiate holder objects: %s' % name)
    global root
    if not root.nodes.has_key(name):
        node = Holder()
        root.nodes[name] = node
    return root.nodes[name]

