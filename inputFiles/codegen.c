record Ali begin
    bool boool = true;
    const int test = false;
    double d_value = .2123;
    float f = 123.;
    const double other = 1.213;
    const long lll = /#123e3#/ 123e-3;
    char character = '\n';
end record;

int global_int_var;
double double_global;
char c;

record Taghi begin
    double a;
end record;

float f_globe;
bool bol_global;

function void test();

function int main()
    begin

        int a = 3;
        double b = 4, dab = 123.23;
        float f1, f2 = true, f3 = 13e2;
        char local_character = 'a', c3 = '\t';

        a = 4;
    end

long long_var;

function long other(double db, long tea, float fa)
    begin
    end

function void test(int a, bool bole, char character, int tea)
    begin
    end

/#function double test(bool a, bool b, char c, int d)
    begin
    end wrong overloading#/

record Vali_vali begin
    bool bol;
end record;