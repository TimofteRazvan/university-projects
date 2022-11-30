clear all;
n = input('Nr. of successes = ');
N = input('Simulations = ');
p = input('Probability = ');

for i = 1:n
    Y(i) = 0;
    while(rand >= p)
        Y(i) = Y(i)+1;
    end
end

for i = 1:N
    for j = 1:n
        Y(j) = 0;
        while(rand >= p)
            Y(j) = Y(j)+1;
        end
    end
    X(i) = sum(Y);
end

U_X = unique(X);
N_X = hist(X, length(U_X));
rel_freq = N_X / N;

x = 1:n;
y = nbinpdf(x, n, p);

plot(x, y, 'x', U_X, rel_freq, 'o');
title("Simulated Pascal (Negative Binomial) Distribution")
legend('NBINPDF', 'SIMULATION');