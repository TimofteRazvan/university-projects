p = input("Probability = ");
N = input("Simulations = ");

% With for
%for i=1:N
%    % The i-th simulation
%    U = rand;
%    X(i) = (U < p);
%end

% Without for
U = rand(1,N);
X = (U < p);

U_X = unique(X); % The unique results that we can get
N_X = hist(X, length(U_X)); % The nr. of appearances
rel_freq = N_X / N; % The relative frequency

[U_X; rel_freq] % The simulated results & their frequencies

%[1:N; X]