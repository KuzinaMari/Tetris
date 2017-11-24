package tetris;

/**
 * Created by 1 on 24.11.2017.
 */
public enum Type {
    L {
        protected void createFields() {
            byte[][] v1 = {
                    {0, 1, 0},
                    {0, 1, 0},
                    {0, 1, 1},
            };
            addField(v1);
            byte[][] v2 = {
                    {0, 0, 0},
                    {1, 1, 1},
                    {1, 0, 0},
            };
            addField(v2);
            byte[][] v3 = {
                    {1, 1, 0},
                    {0, 1, 0},
                    {0, 1, 0},
            };
            addField(v3);
            byte[][] v4 = {
                    {0, 0, 1},
                    {1, 1, 1},
                    {0, 0, 0},
            };
            addField(v4);
        }

    }, //фигура буквой г
    I {
        protected void createFields() {
            byte[][] v1 = {
                    {0, 1, 0, 0},
                    {0, 1, 0, 0},
                    {0, 1, 0, 0},
                    {0, 1, 0, 0}
            };
            addField(v1);
            byte[][] v2 = {
                    {0, 0, 0, 0},
                    {1, 1, 1, 1},
                    {0, 0, 0, 0},
                    {0, 0, 0, 0}
            };
            addField(v2);
            byte[][] v3 = {
                    {0, 0, 1, 0},
                    {0, 0, 1, 0},
                    {0, 0, 1, 0},
                    {0, 0, 1, 0}
            };
            addField(v3);
            byte[][] v4 = {
                    {0, 0, 0, 0},
                    {0, 0, 0, 0},
                    {1, 1, 1, 1},
                    {0, 0, 0, 0}
            };
            addField(v4);
        }
    },
    Z {
        protected void createFields() {
            byte[][] v1 = {
                    {0, 1, 1},
                    {1, 1, 0},
                    {0, 0, 0},
            };
            addField(v1);
            byte[][] v2 = {
                    {0, 1, 0},
                    {0, 1, 1},
                    {0, 0, 1},
            };
            addField(v2);
            byte[][] v3 = {
                    {0, 0, 0},
                    {0, 1, 1},
                    {1, 1, 0},
            };
            addField(v3);
            byte[][] v4 = {
                    {1, 0, 0},
                    {1, 1, 0},
                    {0, 1, 0},
            };
            addField(v4);
        }
    },
    T {
        protected void createFields() {
            byte[][] v1 = {
                    {1, 1, 1},
                    {0, 1, 0},
                    {0, 0, 0},
            };
            addField(v1);
            byte[][] v2 = {
                    {0, 0, 1},
                    {0, 1, 1},
                    {0, 0, 1},
            };
            addField(v2);
            byte[][] v3 = {
                    {0, 0, 0},
                    {0, 1, 0},
                    {1, 1, 1},
            };
            addField(v3);
            byte[][] v4 = {
                    {1, 0, 0},
                    {1, 1, 0},
                    {1, 0, 0},
            };
            addField(v4);
        }
    },
    O {
        protected void createFields() {
            byte[][] v1 = {
                    {1, 1},
                    {1, 1},
            };
            addField(v1);
            byte[][] v2 = {
                    {1, 1},
                    {1, 1},
            };
            addField(v2);
            byte[][] v3 = {
                    {1, 1},
                    {1, 1},
            };
            addField(v3);
            byte[][] v4 = {
                    {1, 1},
                    {1, 1},
            };
            addField(v4);
        }
    },//квадрат
    J {
        protected void createFields() {
            byte[][] v1 = {
                    {0, 1, 0},
                    {0, 1, 0},
                    {1, 1, 0},
            };
            addField(v1);
            byte[][] v2 = {
                    {1, 0, 0},
                    {1, 1, 1},
                    {0, 0, 0},
            };
            addField(v2);
            byte[][] v3 = {
                    {0, 1, 1},
                    {0, 1, 0},
                    {0, 1, 0},
            };
            addField(v3);
            byte[][] v4 = {
                    {0, 0, 0},
                    {1, 1, 1},
                    {0, 0, 1},
            };
            addField(v4);
        }
    }, //обратная г
    S {
        protected void createFields() {
            byte[][] v1 = {
                    {1, 1, 0},
                    {0, 1, 1},
                    {0, 0, 0},
            };
            addField(v1);
            byte[][] v2 = {
                    {0, 0, 1},
                    {0, 1, 1},
                    {0, 1, 0},
            };
            addField(v2);
            byte[][] v3 = {
                    {0, 0, 0},
                    {1, 1, 0},
                    {0, 1, 1},
            };
            addField(v3);
            byte[][] v4 = {
                    {0, 1, 0},
                    {1, 1, 0},
                    {1, 0, 0},
            };
            addField(v4);
        }
    }; // обратная z

    public Canvas[] getFields() {
        return myFields;
    }

    private final Canvas[] myFields = new Canvas[4];

    Type() {
        createFields();
    }

    protected abstract void createFields();

    protected int myCurrentCanvas = -1;

    protected void addField(byte[][] field) {
        Canvas c = new Canvas(field[0].length, field.length);
        c.set(field);
        myCurrentCanvas++;
        myFields[myCurrentCanvas] = c;
    }
}
