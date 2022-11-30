N = input('Simulations = ');
p = input('Probability = ');

X = 0;
while(rand >= p)
    X = X+1;
end

for i = 1:N
    X(i) = 0;
    while (rand >= p)
        X(i) = X(i) + 1;
    end
end

U_X = unique(X);
N_X = hist(X, length(U_X));
rel_freq = N_X / N;

x = 0:20;
y = geopdf(x, p);

plot(x, y, '*', U_X, rel_freq, 'o');
title("Simulated Geometric Distribution");
legend('GEOPDF', 'SIMULATION');