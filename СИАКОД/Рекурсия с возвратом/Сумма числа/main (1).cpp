#include <iostream>
#include <vector>
std::vector<int> vec;
bool PrintFragments (unsigned num, unsigned max, unsigned& nTry, unsigned lvl){
   unsigned n = num;
   for (int n = num; n > 0; --n){
      if (n <= max){
        bool found = false;
        if (n == num && !--nTry)
          found = true;
        else if (PrintFragments (num - n, n, nTry, lvl + 1))
          found = true;
        if (found){
          vec.push_back(n);
          if (!lvl){
            for(std::vector<int>::reverse_iterator it = vec.rbegin(); it != vec.rend(); ++it)
              std::cout << *it << " ";
            vec.clear();
            std::cout << std::endl;
          }
          return true;
        }
      }
  }
  return false;
}

void PrintNumFragmentsInfo (unsigned num){
  unsigned counter = 0;
  while (1){
    unsigned nTry = counter + 1;
    if (PrintFragments (num, num - 1, nTry,0))
        ++counter;
    else
        break;
  }
  std::cout << counter << std::endl;
}

int main (){
   int n;
   std::cin >> n;
   PrintNumFragmentsInfo (n);
}
