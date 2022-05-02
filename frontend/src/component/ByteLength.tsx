/**
 *
 * @param {string} str 문자열
 * @returns {number} 문자열의 Byte Count 반환
 */
const ByteLength = (str: String) => {
  return str
    .split("")
    .map((s: any) => s.charCodeAt(0))
    .reduce((prev: any, c: any) => prev + (c === 10 ? 2 : c >> 7 ? 2 : 1), 0);
};

export default ByteLength;
