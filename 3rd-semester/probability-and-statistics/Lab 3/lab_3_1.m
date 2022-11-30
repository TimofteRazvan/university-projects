fprintf('Distribution types:\n');
fprintf('Normal\nStudent\nChi\nFischer\n');
distribution = input('Type of distribution: ', 's');

switch distribution
    case 'Normal'
        mu = input('mu=');
        sigma = input('sigma=');
        fprintf('Normal distribution:\n');
        A1 = normcdf(0, mu, sigma);
        A2 = 1 - A1;
        B1 = normcdf(1, mu, sigma) - normcdf(-1, mu, sigma);
        aux1 = normcdf(-1, mu, sigma);
        aux2 = 1 - normcdf(1, mu, sigma);
        B2 = aux1 + aux2;
        alpha = input('alpha=');
        beta = input('beta=');
        if alpha > 0 && alpha < 1
            C = norminv(alpha, mu, sigma);
        else
            fprintf('Alpha must be in (0,1)!\n');
        end
        if beta > 0 && beta < 1
            D = norminv(1-beta, mu, sigma);
        else
            fprintf('Beta must be in (0,1)!\n');
        end
    case 'Student'
        n = input('n=');
        fprintf('Student distribution:\n');
        A1 = tcdf(0, n);
        A2 = 1 - A1;
        B1 = tcdf(1, n) - tcdf(-1, n);
        aux1 = tcdf(-1, n);
        aux2 = 1 - tcdf(1, n);
        B2 = aux1 + aux2;
        alpha = input('alpha=');
        beta = input('beta=');
        if alpha > 0 && alpha < 1
            C = tinv(alpha, n);
        else
            fprintf('Alpha must be in (0,1)!\n');
        end
        if beta > 0 && beta < 1
            D = tinv(1-beta, n);
        else
            fprintf('Beta must be in (0,1)!\n');
        end
    case 'Chi'
        n = input('n=');
        fprintf('X Distribution:\n');
        A1 = chi2cdf(0, n);
        A2 = 1 - A1;
        B1 = chi2cdf(1, n) - chi2cdf(-1, n);
        aux1 = chi2cdf(-1, n);
        aux2 = 1 - chi2cdf(1, n);
        B2 = aux1 + aux2;
        alpha = input('alpha=');
        beta = input('beta=');
        if alpha > 0 && alpha < 1
            C = chi2inv(alpha, n);
        else
            fprintf('Alpha must be in (0,1)!\n');
        end
        if beta > 0 && beta < 1
            D = chi2inv(1-beta, n);
        else
            fprintf('Beta must be in (0,1)!\n');
        end
    case 'Fischer'
        n = input('n=');
        m = input('m=');
        fprintf('Fischer distribution:\n');
        A1 = fcdf(0, n, m);
        A2 = 1 - A1;
        B1 = fcdf(1, n, m) - fcdf(-1, n, m);
        aux1 = fcdf(-1, n, m);
        aux2 = 1 - fcdf(1, n, m);
        B2 = aux1 + aux2;
        alpha = input('alpha=');
        beta = input('beta=');
        if alpha > 0 && alpha < 1
            C = finv(alpha, n, m);
        else
            fprintf('Alpha must be in (0,1)!\n');
        end
        if beta > 0 && beta < 1
            D = finv(1-beta, n, m);
        else
            fprintf('Beta must be in (0,1)!\n');
        end
    otherwise
        fprintf('Unknown distribution.\n');
end

fprintf('P(X<=0)=%f\n', A1);
fprintf('P(X>=0)=%f\n', A2);
fprintf('P(-1<=X<=1)=%f\n', B1);
fprintf('P(X<=-1 or X>=1)=%f\n', B2);
fprintf('x(alpha)=%f\n', C);
fprintf('x(beta)=%f\n', D);