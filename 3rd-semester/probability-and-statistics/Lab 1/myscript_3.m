x=linspace(0,3);
f1=(x.^5)/10;
f2=x.*sin(x);
f3=cos(x);

subplot(3,1,1)
plot(x,f1)
grid
subplot(3,1,2)
plot(x,f2)
grid
subplot(3,1,3)
plot(x,f3)
grid