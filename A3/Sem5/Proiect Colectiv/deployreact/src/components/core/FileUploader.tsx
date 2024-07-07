import React, {useRef} from 'react';

interface FileUploaderProps {
  onUpload: (files: FileList) => void;
  children: React.ReactNode;
  accept?: string;
}

export default function FileUploader(props: FileUploaderProps) {
  const {onUpload, children, accept} = props;
  // eslint-disable-next-line @typescript-eslint/no-explicit-any
  const fileInputRef = useRef<any>(null);

  const handleClick = () => {
    fileInputRef.current?.click();
  }

  return (
    <div>
      <label>
        <div onClick={handleClick}>
          {children}
        </div>
        <input
          ref={fileInputRef} type="file" accept={accept} hidden multiple
          onChange={e => onUpload(e.target.files || new FileList())}
        />
      </label>
    </div>
  )
}