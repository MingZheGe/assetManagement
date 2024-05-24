export function amountToArray(amount) {
    // 将数字转化为字符串以处理小数点
    const amountStr = amount.toString();
    
    // 使用正则表达式来匹配整数和小数部分
    const match = amountStr.match(/^(-?\d+)\.(\d+)$/);
    if (match) {
      // 如果匹配成功，将整数和小数部分存入数组并返回
      const integerPart = match[1];
      const decimalPart = match[2];
      return [integerPart, decimalPart];
    } else {
      // 如果匹配失败，返回指定的数组
      return [amountStr, '00'];
    }
  }

  export function sumCount(array) {
    let expend = 0; let income = 0
    array.forEach((item) => {
      if (item.type === 'expend')
        expend += item.amount
      else
        income += item.amount
    })
    return { expend, income }
  }

  export function sumCountLoan(array) {
    let expend = 0; let income = 0
    array.forEach((item) => {
        expend += item.loanAmount
    })
    return { expend}
  }

  export function getMonth(array) {
    let expend = 0; let income = 0
    array.forEach((item) => {
        expend += item.loanAmount
    })
    return { expend}
  }
