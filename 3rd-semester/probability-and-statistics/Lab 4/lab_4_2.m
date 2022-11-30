n = input('Trials = ');
N = input('Simulations = ');
p = input('Probability = ');

for i = 1:N
    U = rand(n, 1);
    X(i) = sum(U < p);
end

%for i=1:N
%    for j=1:n
%        U(i) = rand;
%    end
%    X(i) = sum(U < p);
%end

U_X = unique(X);
N_X = hist(X, length(U_X));
rel_freq = N_X / N;

x = 0:n;
y = binopdf(x, n, p);

plot(x, y, 'x', U_X, rel_freq, 'o');
title("Simulated Binomial Distribution")
legend('BINOPDF', 'SIMULATION');