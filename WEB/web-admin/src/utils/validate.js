/*
 * @Author: xujiafei
 * @Date: 2019-05-23 14:14:57
 * @Last Modified by: xujiafei
 * @Last Modified time: 2019-05-23 18:30:05
 */

export function isNumber(rule, value, callback) {
  const pattern = /^\d+$/;
  if (pattern.test(value)) {
    return callback();
  }
  return callback(new Error(rule.message));
}
export function isListEmpty(rule, value, callback) {
  if (value && value.length > 0) {
    return callback();
  }
  return callback(new Error(rule.message));
}
export function isEmpty(rule, value, callback) {
  if (value && value.trim() !== '') {
    return callback();
  }
  return callback(new Error(rule.message));
}
