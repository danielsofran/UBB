class DuplicateIdError(Exception): # id duplicat
    def __init__(self, message="Id duplicat!", id=None, *args):
        if id is not None:
            l = message.split()
            l.insert(1, str(id))
            message = ' '.join(l)
            super().__init__(message, id, *args)
        else:
            super().__init__(message, *args)