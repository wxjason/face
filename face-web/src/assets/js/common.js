/**
 * 求两个集合的交集
 * @param {Array} a 集合A
 * @param {Array} b 集合B
 * @returns {Array} 两个集合的交集
 */
function arrayIntersection(a, b)
{
  let ai=0, bi=0;
  let result = [];
  while( ai < a.length && bi < b.length )
  {
    if (a[ai] < b[bi] ){ ai++; }
    else if (a[ai] > b[bi] ){ bi++; }
    else /* they're equal */
    {
      result.push(a[ai]);
      ai++;
      bi++;
    }
  }
  return result;
}
function uuid() {
  let s = [];
  let hexDigits = "0123456789ABCDEF";
  for (let i = 0; i < 36; i++) {
    s[i] = hexDigits.substr(Math.floor(Math.random() * 0x10), 1);
  }
  s[14] = "4";  // bits 12-15 of the time_hi_and_version field to 0010
  s[19] = hexDigits.substr((s[19] & 0x3) | 0x8, 1);  // bits 6-7 of the clock_seq_hi_and_reserved to 01
  s[8] = s[13] = s[18] = s[23] = "-";
  return s.join("").replace(/-/g, '');
}

function dateToString(date) {
  let y = date.getFullYear();
  let m = date.getMonth() + 1;
  m = m < 10 ? ('0' + m) : m;
  let d = date.getDate();
  d = d < 10 ? ('0' + d) : d;
  let h = date.getHours();
  h = h < 10 ? ('0' + h) : h;
  let minute = date.getMinutes();
  minute = minute < 10 ? ('0' + minute) : minute;
  let second= date.getSeconds();
  second = second < 10 ? ('0' + second) : second;
  return y + '-' + m + '-' + d+' '+h+':'+minute+':'+ second;
}

function dateToStr(date) {
  let y = date.getFullYear();
  let m = date.getMonth() + 1;
  m = m < 10 ? ('0' + m) : m;
  let d = date.getDate();
  d = d < 10 ? ('0' + d) : d;
  return y + '-' + m + '-' + d;
}

function stringToDate(str) {
  let t = Date.parse(str);
  if (!isNaN(t)) {
    return new Date(Date.parse(str.replace(/-/g, "/")));
  } else {
    return new Date();
  }
}

///计算两个整数的百分比值
function toPpercent(num, total) {
  num = parseFloat(num);
  total = parseFloat(total);
  if (isNaN(num) || isNaN(total)) {
    return "-";
  }
  return total <= 0 ? "0%" : (Math.round(num / total * 10000) / 100.00 + "%");
}
export default {
  uuid,
  dateToString,
  dateToStr,
  stringToDate,
  toPpercent,
  arrayIntersection
}



