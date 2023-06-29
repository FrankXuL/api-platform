import {PageContainer} from '@ant-design/pro-components';
import React, {useEffect, useState} from 'react';
import {List, message} from 'antd';
import {listInterfaceInfoByPageUsingGET} from "@/services/api-backend/interfaceInfoController";

/**
 * 主页
 * @constructor
 */
const welcome: React.FC = () => {
  // eslint-disable-next-line react-hooks/rules-of-hooks
  const [loading, setLoading] = useState(false);
  // eslint-disable-next-line react-hooks/rules-of-hooks
  const [list, setList] = useState<API.InterfaceInfo[]>([]);
  // eslint-disable-next-line react-hooks/rules-of-hooks
  const [total, setTotal] = useState<number>(0);
  const size: number = 10;
  const loadData = async (current = 1, pageSize = size) => {
    setLoading(true);
    await listInterfaceInfoByPageUsingGET({
      current,
      pageSize,
    }).then((res: any) => {
      setList(res?.data?.records ?? []);
      setTotal(res?.data?.total ?? 0)
    }).catch((error: any) => {
      message.error('请求失败，' + error.message);
    }).finally(() => {
      setLoading(false);
    })
  };

  useEffect(() => {
    loadData();
  }, []);

  return (
    <PageContainer title="在线接口开放平台">
      <List
        className="my-list"
        loading={loading}
        itemLayout="horizontal"
        dataSource={list}
        renderItem={(item) => {
          const apiLink = `/interface_info/${item.id}`;
          return (
            <List.Item actions={[<a key={item.id} href={apiLink}>查看</a>]}>
              <List.Item.Meta
                title={<a href={apiLink}>{item.name}</a>}
                description={item.description}
              />
            </List.Item>
          );
        }}
        pagination={{
          // eslint-disable-next-line @typescript-eslint/no-shadow
          showTotal(total: number) {
            return '总数：' + total;
          },
          pageSize: size,
          total,
          onChange(page, pageSize) {
            loadData(page, pageSize);
          },
        }}
      />
    </PageContainer>
  );
};

export default welcome;
