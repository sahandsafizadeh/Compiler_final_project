int global_int;
double global_double;
bool global_bool;

function int main() begin
    global_int = 2;
    global_double = 8e+4;
    global_bool = false;

    switch(global_int) of:
    begin
        case 4:
        begin
            print(global_bool);
        end
        case 10:
        begin
            print(global_double);
        end
        case 1:
        begin
            global_int /= 5;
            print(global_int);
        end
        default:
        begin
            print(global_int + global_double);
        end
    end
end