double global_double;

function void add(double d);

function double add(double d1, double d2);

function int main() begin
    const float var = (double).123;
    const double parameter1 = 12e-2;
    global_double = 10;
    add(parameter1);

    double result = add(parameter1, parameter1 + 0.2);
    print(global_double);
    print(result);
end

function void add(double d) begin
    global_double += d;
end

function double add(double d1, double d2) begin
    return d1 + d2;
end